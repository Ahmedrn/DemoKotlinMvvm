package com.skom.demokotlinmvvm.domain

data class Article(
    val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String,
    val author: String) {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }
}