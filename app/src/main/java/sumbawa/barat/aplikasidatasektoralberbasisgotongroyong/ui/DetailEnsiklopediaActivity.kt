package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_ensiklopedia.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R

class DetailEnsiklopediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ensiklopedia)
        val intentFotoEnsiklopedia = intent.getStringExtra("intent_foto_ensiklopedia")
        val intentJudulEnsiklopedia = intent.getStringExtra("intent_judul_ensiklopedia")
        val intentDeskripsi = intent.getStringExtra("intent_deskripsi")

        this.title = "Deskripsi Ensiklopedia"

        this.tvDetailJudulEnsiklopedia.text = intentJudulEnsiklopedia
        this.tvDetailIsiDeskripsi.text = intentDeskripsi

        Glide.with(this)
            .load(intentFotoEnsiklopedia )
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailFotoEnsiklopedia)
    }
}