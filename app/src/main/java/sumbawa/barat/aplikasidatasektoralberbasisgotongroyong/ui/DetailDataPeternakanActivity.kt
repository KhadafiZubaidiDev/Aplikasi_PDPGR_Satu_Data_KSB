package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_data_peternakan.*
import kotlinx.android.synthetic.main.activity_detail_puskesmas.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R

class DetailDataPeternakanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data_peternakan)
        val intentFotoPeternakan = intent.getStringExtra("intent_foto_peternakan")
        val intentJudulData = intent.getStringExtra("intent_judul_data")
        val intentDeskripsiData = intent.getStringExtra("intent_deskripsi_data")
        val intentUrlChartPeternakan1 = intent.getStringExtra("intent_url_chart_peternakan_1")
        val intentUrlChartPeternakan2 = intent.getStringExtra("intent_url_chart_peternakan_2")

        this.title = "Detail Informasi Peternakan"

        this.tvDetailDeskripsiData.text = intentDeskripsiData

        Glide.with(this)
            .load(intentFotoPeternakan )
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailFotoPeternakan)

        Glide.with(this)
            .load(intentUrlChartPeternakan1 )
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailChart1)

        Glide.with(this)
            .load(intentUrlChartPeternakan2 )
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailChart2)
    }
}