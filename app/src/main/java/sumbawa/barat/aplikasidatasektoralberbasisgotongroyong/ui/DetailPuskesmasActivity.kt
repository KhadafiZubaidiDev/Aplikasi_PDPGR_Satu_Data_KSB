package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_ensiklopedia.*
import kotlinx.android.synthetic.main.activity_detail_puskesmas.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R

class DetailPuskesmasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_puskesmas)
        val intentFotoPuskesmas = intent.getStringExtra("intent_foto_puskesmas")
        val intentKodePuskesmas = intent.getStringExtra("intent_kode_puskesmas")
        val intentNamaPuskesmas = intent.getStringExtra("intent_nama_puskesmas")
        val intentNamaKecamatan = intent.getStringExtra("intent_nama_kecamatan")
        val intentAlamatPuskesmas = intent.getStringExtra("intent_alamat_puskesmas")
        val intentKemampuanLayanan = intent.getStringExtra("intent_kemampuan_layanan")
        val intentJmlPustu = intent.getStringExtra("intent_jml_pustu")
        val intentJmlPolindesPoskesdes = intent.getStringExtra("intent_jml_polindes_poskesdes")
        val intentJmlPoskestren = intent.getStringExtra("intent_jml_poskestren")
        val intentJmlDesaSiaga = intent.getStringExtra("intent_jml_desa_siaga")
        val intentJmlPosyandu = intent.getStringExtra("intent_jml_posyandu")

        this.title = "Detail Informasi Puskesmas"

        this.tvDetailKodePuskesmas.text = intentKodePuskesmas
        this.tvDetailNamaPuskesmas.text = intentNamaPuskesmas
        this.tvDetailNamaKecamatan.text = intentNamaKecamatan
        this.tvDetailAlamatPuskesmas.text = intentAlamatPuskesmas
        this.tvDetailKemampuanLayanan.text = intentKemampuanLayanan
        this.tvDetailJumlahPustu.text = intentJmlPustu
        this.tvDetailJumlahPolindesPoskesdes.text = intentJmlPolindesPoskesdes
        this.tvDetailJumlahPoskestren.text = intentJmlPoskestren
        this.tvDetailJumlahDesaSiaga.text = intentJmlDesaSiaga
        this.tvDetailJumlahPosyandu.text = intentJmlPosyandu

        Glide.with(this)
            .load(intentFotoPuskesmas )
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(ivDetailFotoPuskesmas)

    }
}