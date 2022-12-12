package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import io.ktor.util.*
import kotlinx.android.synthetic.main.activity_kecamatan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewKecamatanAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.KecamatanApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity

class KecamatanActivity : BaseActivity() {

    lateinit var adapter: RecyclerviewKecamatanAdapter

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kecamatan)
        adapter = RecyclerviewKecamatanAdapter()
        rvKecamatan.adapter = adapter
        tampilkanSemuaKecamatan()
        this.title = "Data Kecamatan"
    }

    @KtorExperimentalAPI
    private fun tampilkanSemuaKecamatan()  {
        CoroutineScope(Dispatchers.IO).launch {
            val kecamatanApi = KecamatanApi()
            val response = kecamatanApi.tampilDataKecamatanSeluruhnya()
            withContext(Dispatchers.Main) {
                adapter.setKecamatanItems(response)
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
                adapter = RecyclerviewKecamatanAdapter()
                rvKecamatan.adapter = adapter
                tampilkanSemuaKecamatan()
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