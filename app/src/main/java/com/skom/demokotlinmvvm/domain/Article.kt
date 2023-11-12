package com.skom.demokotlinmvvm.domain

data class Article(
    val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String,
    val author: String) {
}