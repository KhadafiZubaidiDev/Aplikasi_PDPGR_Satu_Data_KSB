package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import io.ktor.util.*
import kotlinx.android.synthetic.main.activity_berita.*
import kotlinx.android.synthetic.main.activity_ensiklopedia.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewEnsiklopediaAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.EnsiklopediaApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Ensiklopedia

class EnsiklopediaActivity : BaseActivity() {

    lateinit var adapter: RecyclerviewEnsiklopediaAdapter

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ensiklopedia)
        adapter = RecyclerviewEnsiklopediaAdapter()
        rvEnsiklopedia.adapter = adapter
        tampilkanSemuaEnsiklopedia()
        this.title = "Data Ensiklopedia"
        adapter.setOnItemClickListener(object : ClickListener<Ensiklopedia>{
            override fun onClick(view: View?, data: Ensiklopedia, position: Int){
                startActivity(
                    Intent(this@EnsiklopediaActivity, DetailEnsiklopediaActivity::class.java)
                        .putExtra("intent_id", data.id)
                        .putExtra("intent_foto_ensiklopedia", data.url_foto_ensiklopedia)
                        .putExtra("intent_judul_ensiklopedia", data.judul_ensiklopedia)
                        .putExtra("intent_deskripsi", data.deskripsi)

                )
            }
        })
    }

    @KtorExperimentalAPI
    private fun tampilkanSemuaEnsiklopedia()  {
        CoroutineScope(Dispatchers.IO).launch {
            val ensiklopediaApi = EnsiklopediaApi()
            val response = ensiklopediaApi.tampilDataEnsklopediaSeluruhnya()
            withContext(Dispatchers.Main) {
                adapter.setEnsiklopediaItems(response)
            }
        }
    }

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pilihan_pencarian_data, menu)

        val item = menu.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.d("onQueryTextChange", "query: " + query)
                adapter.filter.filter(query)
                return true
            }
        })

        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                adapter.filter.filter("")
                adapter = RecyclerviewEnsiklopediaAdapter()
                rvBerita.adapter = adapter
                tampilkanSemuaEnsiklopedia()
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.action_menu_utama -> {
                val i = Intent(this, MenuUtamaActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}