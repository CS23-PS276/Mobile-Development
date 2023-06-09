package com.cs23_ps276.sahabatlansia.api

data class FinderRequest(
    val Mobilitas: Int? = null,
    val Penyakitlain: Int? = null,
    val Hipertensi: Int? = null,
    val Diabetes: Int? = null,
    val Reumatik: Int? = null,
    val Penyakitjantung: Int? = null,
    val Asma: Int? = null,
    val Stroke: Int? = null,
    val Mengurusrumah: Int? = null,
    val Membantupergerakandanaktivitasfisik: Int? = null,
    val Membantukonsumsiobatdanmakanan: Int? = null,
    val Mengecekkesehatanrutinsecaramandiri: Int? = null,
    val Mendampingidanmenjaga: Int? = null,
    val Memasangkanalatmediskhusus: Int? = null,
    val Memeriksakanrutinkedokter: Int? = null,
    val Indonesia: Int? = null,
    val Inggris: Int? = null,
    val Jawa: Int? = null,
    val Sunda: Int? = null,
    val Melayu: Int? = null,
    val Kota: String? = null,
)