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
import kotlinx.android.synthetic.main.activity_ensiklopedia.*
import kotlinx.android.synthetic.main.activity_puskesmas.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.`interface`.ClickListener
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewEnsiklopediaAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.adapter.RecyclerviewPuskesmasAdapter
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.EnsiklopediaApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api.PuskesmasApi
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Ensiklopedia
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Puskesmas

class PuskesmasActivity : BaseActivity() {

    lateinit var adapter: RecyclerviewPuskesmasAdapter

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puskesmas)
        adapter = RecyclerviewPuskesmasAdapter()
        rvPuskesmas.adapter = adapter
        tampilkanSemuaPuskesmas()
        this.title = "Data Puskesmas"
        adapter.setOnItemClickListener(object : ClickListener<Puskesmas> {
            override fun onClick(view: View?, data: Puskesmas, position: Int){
                startActivity(
                    Intent(this@PuskesmasActivity, DetailPuskesmasActivity::class.java)
                        .putExtra("intent_id", data.id)
                        .putExtra("intent_foto_puskesmas", data.url_foto_puskesmas)
                        .putExtra("intent_kode_puskesmas", data.kode_puskesmas)
                        .putExtra("intent_nama_puskesmas", data.nama_puskesmas)
                        .putExtra("intent_nama_kecamatan", data.nama_kecamatan)
                        .putExtra("intent_alamat_puskesmas", data.alamat_puskesmas)
                        .putExtra("intent_kemampuan_layanan", data.kemampuan_layanan)
                        .putExtra("intent_jml_pustu", data.jml_pustu)
                        .putExtra("intent_jml_polindes_poskesdes", data.jml_polindes_poskesdes)
                        .putExtra("intent_jml_poskestren", data.jml_poskestren)
                        .putExtra("intent_jml_desa_siaga", data.jml_desa_siaga)
                        .putExtra("intent_jml_posyandu", data.jml_posyandu)
                )
            }
        })
    }

    @KtorExperimentalAPI
    private fun tampilkanSemuaPuskesmas()  {
        CoroutineScope(Dispatchers.IO).launch {
            val puskesmasApi = PuskesmasApi()
            val response = puskesmasApi.tampilDataPuskesmasSeluruhnya()
            withContext(Dispatchers.Main) {
                adapter.setPuskesmasItems(response)
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
                adapter = RecyclerviewPuskesmasAdapter()
                rvBerita.adapter = adapter
                tampilkanSemuaPuskesmas()
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