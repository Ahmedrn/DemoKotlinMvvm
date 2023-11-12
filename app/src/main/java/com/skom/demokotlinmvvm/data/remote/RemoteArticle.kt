package com.skom.demokotlinmvvm.data.remote

import com.skom.demokotlinmvvm.data.local.LocalArticle

class RemoteArticle(
    val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String?,
    val author: String) {

    fun maptoLocalArticle() = LocalArticle(id, title, author, body, imageUrl)
}