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
import kotlinx.android.synthetic.main.activity_peternakan.*
import kotlinx.android.synthetic.main.activity_puskesmas.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewPeternakanAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewPuskesmasAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.PeternakanApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.PuskesmasApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Peternakan
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Puskesmas

class PeternakanActivity : BaseActivity() {

    lateinit var adapter: RecyclerviewPeternakanAdapter

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peternakan)
        adapter = RecyclerviewPeternakanAdapter()
        rvPeternakan.adapter = adapter
        tampilkanSemuaDataPeternakan()
        this.title = "Data Peternakan"
        adapter.setOnItemClickListener(object : ClickListener<Peternakan> {
            override fun onClick(view: View?, data: Peternakan, position: Int){
                startActivity(
                    Intent(this@PeternakanActivity, DetailDataPeternakanActivity::class.java)
                        .putExtra("intent_id", data.id)
                        .putExtra("intent_foto_peternakan", data.url_foto_peternakan)
                        .putExtra("intent_judul_data", data.judul_data)
                        .putExtra("intent_deskripsi_data", data.deskripsi_data)
                        .putExtra("intent_url_chart_peternakan_1", data.url_chart_peternakan_1)
                        .putExtra("intent_url_chart_peternakan_2", data.url_chart_peternakan_2)
                )
            }
        })
    }

    @KtorExperimentalAPI
    private fun tampilkanSemuaDataPeternakan()  {
        CoroutineScope(Dispatchers.IO).launch {
            val peternakanApi = PeternakanApi()
            val response = peternakanApi.tampilDataPeternakanSeluruhnya()
            withContext(Dispatchers.Main) {
                adapter.setPeternakanItems(response)
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
                adapter = RecyclerviewPeternakanAdapter()
                rvBerita.adapter = adapter
                tampilkanSemuaDataPeternakan()
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