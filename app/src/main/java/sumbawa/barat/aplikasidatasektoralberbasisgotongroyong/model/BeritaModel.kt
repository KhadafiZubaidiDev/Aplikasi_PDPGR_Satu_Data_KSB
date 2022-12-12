package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model

data class Berita (
    val id: Int,
    val judul_berita: String,
    val isi_berita: String,
    val tanggal_berita: String,
    val sumber_berita: String,
    val url_foto_berita: String,
)