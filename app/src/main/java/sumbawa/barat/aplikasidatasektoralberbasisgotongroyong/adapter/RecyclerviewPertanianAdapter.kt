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
import kotlinx.android.synthetic.main.adapter_pertanian.view.*
import kotlinx.android.synthetic.main.adapter_peternakan.view.*
import kotlinx.android.synthetic.main.adapter_puskesmas.view.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Pertanian
import java.util.*

class RecyclerviewPertanianAdapter: RecyclerView.Adapter<MyViewHolderPertanian>(), Filterable{
    private var pertanianList = mutableListOf<Pertanian>()
    private var clickListener: ClickListener<Pertanian>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPertanian {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pertanian, parent, false)
        return MyViewHolderPertanian(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderPertanian, position: Int) {
        val pertanian = pertanianList[position]
        holder.judul_data.text = pertanian.judul_data
        Glide.with(holder.itemView.context)
            .load(pertanian.url_foto_pertanian)
            .into(holder.url_foto_pertanian)
        //onclicklistener
        holder.itemView.setOnClickListener { v -> clickListener!!.onClick(v, pertanian, position) }
    }

    fun setOnItemClickListener(clickListener: ClickListener<Pertanian>) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return pertanianList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPertanianItems(pertanianList: List<Pertanian>) {
        this.pertanianList = pertanianList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter():Filter{
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    pertanianList as ArrayList<Pertanian>
                } else {
                    val resultList = ArrayList<Pertanian>()
                    for (row in pertanianList) {
                        if (row.judul_data.lowercase(Locale.ROOT)
                                .contains(constraint.toString().lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    pertanianList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = pertanianList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                pertanianList = results?.values as ArrayList<Pertanian>
                notifyDataSetChanged()
            }
        }
    }
}

class MyViewHolderPertanian(view: View) : RecyclerView.ViewHolder(view) {
    val url_foto_pertanian = view.ivFotoPertanian
    val judul_data = view.tvJudulDataPertanian
}
