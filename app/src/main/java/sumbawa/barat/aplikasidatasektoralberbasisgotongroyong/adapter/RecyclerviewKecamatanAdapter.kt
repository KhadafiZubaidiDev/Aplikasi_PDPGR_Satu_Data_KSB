package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_kecamatan.view.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Kecamatan
import java.util.*

class RecyclerviewKecamatanAdapter: RecyclerView.Adapter<MyViewHolderKecamatan>(), Filterable {

    private var kecamatanList = mutableListOf<Kecamatan>()
    private var clickListener: ClickListener<Kecamatan>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderKecamatan {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_kecamatan, parent, false)
        return MyViewHolderKecamatan(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderKecamatan, position: Int) {
        val kecamatan = kecamatanList[position]
        holder.nama_kecamatan.text = kecamatan.nama_kecamatan
        holder.jumlah_desa_kelurahan.text = kecamatan.jumlah_desa_kelurahan
        Glide.with(holder.itemView.context)
            .load(kecamatan.url_foto_kantor_camat)
            .into(holder.url_foto_kantor_camat)
        //onclicklistener
        holder.itemView.setOnClickListener { v -> clickListener!!.onClick(v, kecamatan, position) }
    }

    fun setOnItemClickListener(clickListener: ClickListener<Kecamatan>) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return kecamatanList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setKecamatanItems(kecamatanList: List<Kecamatan>) {
        this.kecamatanList = kecamatanList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    kecamatanList as ArrayList<Kecamatan>
                } else {
                    val resultList = ArrayList<Kecamatan>()
                    for (row in kecamatanList) {
                        if (row.nama_kecamatan.lowercase(Locale.ROOT)
                                .contains(constraint.toString().lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    kecamatanList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = kecamatanList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                kecamatanList = results?.values as ArrayList<Kecamatan>
                notifyDataSetChanged()
            }
        }
    }
}

class MyViewHolderKecamatan(view: View) : RecyclerView.ViewHolder(view) {
    val url_foto_kantor_camat = view.ivFotoKantorCamat
    val nama_kecamatan = view.tvNamaKecamatan
    val jumlah_desa_kelurahan = view.tvJumlahDesaKelurahan
}