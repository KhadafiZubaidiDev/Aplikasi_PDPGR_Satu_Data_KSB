package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui.InfoRilisActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui.Login2Activity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui.LoginActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui.MenuUtamaActivity

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = "Selamat Datang"
        val btnMenuUtama1: Button = findViewById(R.id.btnMenuUtama1)
        btnMenuUtama1.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when (v.id){
            R.id.btnMenuUtama1 -> {
                val moveIntent = Intent(this@MainActivity, MenuUtamaActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pilihan_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.menu1 -> {
                val i = Intent(this, Login2Activity::class.java)
                startActivity(i)
                return true
            }
            R.id.menu2 -> {
                val i = Intent(this, InfoRilisActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}