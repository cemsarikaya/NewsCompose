package com.cemsarikaya.newscompose.model

data class NewsList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)