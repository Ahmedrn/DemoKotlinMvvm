package com.skom.demokotlinmvvm.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skom.demokotlinmvvm.data.local.LocalArticle.Companion.TABLE_NAME
import com.skom.demokotlinmvvm.domain.Article

@Entity(tableName = TABLE_NAME)
data class LocalArticle(

    @PrimaryKey
    var id: Int? = 0,
    var title: String? = null,
    var author: String? = null,
    var body: String? = null,
    var imageUrl: String? = null
) {
    companion object {
        const val TABLE_NAME = "demokotlinmvvm_articles"
    }

    fun mapToArticle() = Article(
        id ?: 0,
        title ?: "",
        author ?: "",
        body ?: "",
        imageUrl ?: ""
    )


}