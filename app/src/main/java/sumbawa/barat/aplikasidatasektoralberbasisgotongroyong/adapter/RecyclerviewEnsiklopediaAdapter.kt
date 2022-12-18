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
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Ensiklopedia
import java.util.*

class RecyclerviewEnsiklopediaAdapter: RecyclerView.Adapter<MyViewHolderEnsiklopedia>(), Filterable{
    private var ensiklopediaList = mutableListOf<Ensiklopedia>()
    private var clickListener: ClickListener<Ensiklopedia>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderEnsiklopedia {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_ensiklopedia, parent, false)
        return MyViewHolderEnsiklopedia(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderEnsiklopedia, position: Int) {
        val ensiklopedia = ensiklopediaList[position]
        holder.judul_ensiklopedia.text = ensiklopedia.judul_ensiklopedia
        Glide.with(holder.itemView.context)
            .load(ensiklopedia.url_foto_ensiklopedia)
            .into(holder.url_foto_ensiklopedia)
        //onclicklistener
        holder.itemView.setOnClickListener { v -> clickListener!!.onClick(v, ensiklopedia, position) }
    }

    fun setOnItemClickListener(clickListener: ClickListener<Ensiklopedia>) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return ensiklopediaList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEnsiklopediaItems(ensiklopediaList: List<Ensiklopedia>) {
        this.ensiklopediaList = ensiklopediaList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter():Filter{
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    ensiklopediaList as ArrayList<Ensiklopedia>
                } else {
                    val resultList = ArrayList<Ensiklopedia>()
                    for (row in ensiklopediaList) {
                        if (row.judul_ensiklopedia.lowercase(Locale.ROOT)
                                .contains(constraint.toString().lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    ensiklopediaList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = ensiklopediaList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ensiklopediaList = results?.values as ArrayList<Ensiklopedia>
                notifyDataSetChanged()
            }
        }
    }
}

class MyViewHolderEnsiklopedia(view: View) : RecyclerView.ViewHolder(view) {
    val url_foto_ensiklopedia = view.ivFotoEnsiklopedia
    val judul_ensiklopedia = view.tvNamaEnsiklopdia
}