package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model

data class Puskesmas(
    val id: Int,
    val kode_puskesmas: String,
    val nama_kecamatan: String,
    val nama_puskesmas: String,
    val alamat_puskesmas: String,
    val kemampuan_layanan: String,
    val jml_pustu: String,
    val jml_polindes_poskesdes: String,
    val jml_poskestren: String,
    val jml_desa_siaga: String,
    val jml_posyandu: String,
    val url_foto_puskesmas: String,
)
