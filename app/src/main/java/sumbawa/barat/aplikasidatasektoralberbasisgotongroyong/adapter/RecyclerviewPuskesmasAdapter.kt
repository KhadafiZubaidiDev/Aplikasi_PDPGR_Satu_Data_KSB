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
import kotlinx.android.synthetic.main.adapter_ensiklopedia.view.*
import kotlinx.android.synthetic.main.adapter_puskesmas.view.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Ensiklopedia
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Puskesmas
import java.util.*

class RecyclerviewPuskesmasAdapter: RecyclerView.Adapter<MyViewHolderPuskesmas>(), Filterable{
    private var puskesmasList = mutableListOf<Puskesmas>()
    private var clickListener: ClickListener<Puskesmas>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPuskesmas {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_puskesmas, parent, false)
        return MyViewHolderPuskesmas(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderPuskesmas, position: Int) {
        val puskesmas = puskesmasList[position]
        holder.nama_puskesmas.text = puskesmas.nama_puskesmas
        Glide.with(holder.itemView.context)
            .load(puskesmas.url_foto_puskesmas)
            .into(holder.url_foto_puskesmas)
        //onclicklistener
        holder.itemView.setOnClickListener { v -> clickListener!!.onClick(v, puskesmas, position) }
    }

    fun setOnItemClickListener(clickListener: ClickListener<Puskesmas>) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return puskesmasList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPuskesmasItems(puskesmasList: List<Puskesmas>) {
        this.puskesmasList = puskesmasList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter():Filter{
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    puskesmasList as ArrayList<Puskesmas>
                } else {
                    val resultList = ArrayList<Puskesmas>()
                    for (row in puskesmasList) {
                        if (row.nama_puskesmas.lowercase(Locale.ROOT)
                                .contains(constraint.toString().lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    puskesmasList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = puskesmasList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                puskesmasList = results?.values as ArrayList<Puskesmas>
                notifyDataSetChanged()
            }
        }
    }
}

class MyViewHolderPuskesmas(view: View) : RecyclerView.ViewHolder(view) {
    val url_foto_puskesmas = view.ivFotoPuskesmas
    val nama_puskesmas = view.tvNamaPuskesmas
}