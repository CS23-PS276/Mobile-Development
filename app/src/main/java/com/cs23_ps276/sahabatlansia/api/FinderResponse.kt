package com.cs23_ps276.sahabatlansia.api

data class FinderResponse(
    val result: List<ResultItem?>? = null
)

data class ResultItem(
    val kota: String? = null,
    val layanan: String? = null,
    val umur: Int? = null,
    val bahasa: String? = null,
    val gender: Int? = null,
    val nama: String? = null,
    val pendidikan: Int? = null,
    val tahunPengalaman: Int? = null,
    val gaji: Int? = null,
    val id: Int? = null,
    val nomor: String? = null,
    val jumlahLansiaPernahDirawat: Int? = null
)