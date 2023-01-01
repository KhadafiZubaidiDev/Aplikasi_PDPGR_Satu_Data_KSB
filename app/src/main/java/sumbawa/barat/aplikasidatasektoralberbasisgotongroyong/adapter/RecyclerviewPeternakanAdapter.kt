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
import kotlinx.android.synthetic.main.adapter_peternakan.view.*
import kotlinx.android.synthetic.main.adapter_puskesmas.view.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Ensiklopedia
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Peternakan
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Puskesmas
import java.util.*

class RecyclerviewPeternakanAdapter: RecyclerView.Adapter<MyViewHolderPeternakan>(), Filterable{
    private var peternakanList = mutableListOf<Peternakan>()
    private var clickListener: ClickListener<Peternakan>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPeternakan {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_peternakan, parent, false)
        return MyViewHolderPeternakan(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderPeternakan, position: Int) {
        val peternakan = peternakanList[position]
        holder.judul_data.text = peternakan.judul_data
        Glide.with(holder.itemView.context)
            .load(peternakan.url_foto_peternakan)
            .into(holder.url_foto_peternakan)
        //onclicklistener
        holder.itemView.setOnClickListener { v -> clickListener!!.onClick(v, peternakan, position) }
    }

    fun setOnItemClickListener(clickListener: ClickListener<Peternakan>) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return peternakanList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPeternakanItems(peternakanList: List<Peternakan>) {
        this.peternakanList = peternakanList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter():Filter{
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    peternakanList as ArrayList<Peternakan>
                } else {
                    val resultList = ArrayList<Peternakan>()
                    for (row in peternakanList) {
                        if (row.judul_data.lowercase(Locale.ROOT)
                                .contains(constraint.toString().lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    peternakanList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = peternakanList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                peternakanList = results?.values as ArrayList<Peternakan>
                notifyDataSetChanged()
            }
        }
    }
}

class MyViewHolderPeternakan(view: View) : RecyclerView.ViewHolder(view) {
    val url_foto_peternakan = view.ivFotoPeternakan
    val judul_data = view.tvJudulDataPeternakan
}