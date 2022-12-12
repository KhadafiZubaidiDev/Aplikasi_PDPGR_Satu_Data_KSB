package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_berita.view.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Berita
import java.util.*

class RecyclerviewBeritaAdapter: RecyclerView.Adapter<MyViewHolderBerita>(), Filterable {

    private var beritaList = mutableListOf<Berita>()
    private var clickListener: ClickListener<Berita>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderBerita {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_berita, parent, false)
        return MyViewHolderBerita(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderBerita, position: Int) {
        val berita = beritaList[position]
        holder.judul_berita.text = berita.judul_berita
        holder.tanggal_berita.text = berita.tanggal_berita
        holder.sumber_berita.text = berita.sumber_berita
        Glide.with(holder.itemView.context)
            .load(berita.url_foto_berita)
            .into(holder.url_foto_berita)
        //onclicklistener
        holder.itemView.setOnClickListener { v -> clickListener!!.onClick(v, berita, position) }
    }

    fun setOnItemClickListener(clickListener: ClickListener<Berita>) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBeritaItems(beritaList: List<Berita>) {
        this.beritaList = beritaList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter():Filter{
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    beritaList as ArrayList<Berita>
                } else {
                    val resultList = ArrayList<Berita>()
                    for (row in beritaList) {
                        if (row.judul_berita.lowercase(Locale.ROOT)
                                .contains(constraint.toString().lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    beritaList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = beritaList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                beritaList = results?.values as ArrayList<Berita>
                notifyDataSetChanged()
            }
        }
    }
}

class MyViewHolderBerita(view: View) : RecyclerView.ViewHolder(view) {
    val url_foto_berita = view.ivFotoBerita
    val judul_berita = view.tvJudulBerita
    val sumber_berita = view.tvSumberBerita
    val tanggal_berita = view.tvTanggalBerita
}