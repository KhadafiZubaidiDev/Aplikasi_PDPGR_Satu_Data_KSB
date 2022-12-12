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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewBeritaAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.BeritaApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Berita

class BeritaActivity : BaseActivity() {

    lateinit var adapter: RecyclerviewBeritaAdapter

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita)
        adapter = RecyclerviewBeritaAdapter()
        rvBerita.adapter = adapter
        tampilkanSemuaBerita()
        this.title = "Data Berita"
        adapter.setOnItemClickListener(object : ClickListener<Berita> {
            override fun onClick(view: View?, data: Berita, position: Int) {
                startActivity(
                    Intent(this@BeritaActivity, DetailBeritaActivity::class.java)
                        .putExtra("intent_id", data.id)
                        .putExtra("intent_foto_berita", data.url_foto_berita)
                        .putExtra("intent_judul_berita", data.judul_berita)
                        .putExtra("intent_isi_berita", data.isi_berita)
                        .putExtra("intent_tanggal_berita", data.tanggal_berita)
                        .putExtra("intent_sumber_berita", data.sumber_berita)
                )
            }
        })
    }

    @KtorExperimentalAPI
    private fun tampilkanSemuaBerita()  {
        CoroutineScope(Dispatchers.IO).launch {
            val beritaApi = BeritaApi()
            val response = beritaApi.tampilDataBeritaSeluruhnya()
            withContext(Dispatchers.Main) {
                adapter.setBeritaItems(response)
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
                adapter = RecyclerviewBeritaAdapter()
                rvBerita.adapter = adapter
                tampilkanSemuaBerita()
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