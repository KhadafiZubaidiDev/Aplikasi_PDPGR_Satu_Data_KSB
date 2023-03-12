package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_data_pertanian.*
import kotlinx.android.synthetic.main.activity_detail_data_peternakan.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R

class DetailDataPertanianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data_pertanian)

        val intentFotoPertanian = intent.getStringExtra("intent_foto_pertanian")
        val intentJudulData = intent.getStringExtra("intent_judul_data")
        val intentDeskripsiData = intent.getStringExtra("intent_deskripsi_data")
        val intentUrlChartPertanian = intent.getStringExtra("intent_url_chart_pertanian")

        this.title = "Detail Informasi Pertanian"

        this.tvDetailDeskripsiDataPertanian.text = intentDeskripsiData

        Glide.with(this)
            .load(intentFotoPertanian)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailFotoPertanian)

        Glide.with(this)
            .load(intentUrlChartPertanian)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailChartPertanian)
    }
}