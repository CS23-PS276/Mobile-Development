package com.cs23_ps276.sahabatlansia.api

data class ArticleResponse(
    val result: List<Article>
)

data class Article(
    val id: Int,
    val judul: String,
    val konten: String
)
