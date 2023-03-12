package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import io.ktor.util.*
import kotlinx.android.synthetic.main.activity_berita.*
import kotlinx.android.synthetic.main.activity_pertanian.*
import kotlinx.android.synthetic.main.activity_peternakan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewPertanianAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewPeternakanAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.PertanianApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.PeternakanApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Pertanian
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Peternakan

class PertanianActivity : BaseActivity() {

    lateinit var adapter: RecyclerviewPertanianAdapter

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pertanian)
        adapter = RecyclerviewPertanianAdapter()
        rvPertanian.adapter = adapter
        tampilkanSemuaDataPertanian()
        this.title = "Data Pertanian"
        adapter.setOnItemClickListener(object : ClickListener<Pertanian> {
            override fun onClick(view: View?, data: Pertanian, position: Int){
                startActivity(
                    Intent(this@PertanianActivity, DetailDataPertanianActivity::class.java)
                        .putExtra("intent_id", data.id)
                        .putExtra("intent_foto_pertanian", data.url_foto_pertanian)
                        .putExtra("intent_judul_data", data.judul_data)
                        .putExtra("intent_deskripsi_data", data.deskripsi_data)
                        .putExtra("intent_url_chart_pertanian", data.url_chart_pertanian)
                )
            }
        })
    }

    @KtorExperimentalAPI
    private fun tampilkanSemuaDataPertanian()  {
        CoroutineScope(Dispatchers.IO).launch {
            val pertanianApi = PertanianApi()
            val response = pertanianApi.tampilDataPertanianSeluruhnya()
            withContext(Dispatchers.Main) {
                adapter.setPertanianItems(response)
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
                adapter = RecyclerviewPertanianAdapter()
                rvBerita.adapter = adapter
                tampilkanSemuaDataPertanian()
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