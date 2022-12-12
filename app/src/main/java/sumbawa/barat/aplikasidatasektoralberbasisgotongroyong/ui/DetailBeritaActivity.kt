package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_berita.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity

class DetailBeritaActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)
        val intentFotoBerita = intent.getStringExtra("intent_foto_berita")
        val intentJudulBerita = intent.getStringExtra("intent_judul_berita")
        val intentIsiBerita = intent.getStringExtra("intent_isi_berita")
        val intentSumberBerita = intent.getStringExtra("intent_sumber_berita")
        val intentTanggalBerita = intent.getStringExtra("intent_tanggal_berita")

        this.title = "Detail Berita"

        this.tvDetailJudulBerita.text = intentJudulBerita
        this.tvDetailTanggalBerita.text = intentTanggalBerita
        this.tvDetailSumberBerita.text = intentSumberBerita
        this.tvDetailIsiBerita.text = intentIsiBerita

        Glide.with(this)
            .load(intentFotoBerita )
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailFotoBerita)
    }
}