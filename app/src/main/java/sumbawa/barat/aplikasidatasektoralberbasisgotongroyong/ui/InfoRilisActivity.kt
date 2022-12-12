package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.MainActivity
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity

class InfoRilisActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_rilis)
        this.title = "Info Rilis"
        val btnMenuUtama2: Button = findViewById(R.id.btnMenuUtama2)
        btnMenuUtama2.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when (v.id){
            R.id.btnMenuUtama2 -> {
                val moveIntent = Intent(this@InfoRilisActivity, MenuUtamaActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pilihan_ke_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.menu_home -> {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}