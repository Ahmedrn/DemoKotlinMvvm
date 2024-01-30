package com.skom.demokotlinmvvm.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(posts: List<LocalArticle>)

    @Query("DELETE FROM ${LocalArticle.TABLE_NAME}")
    suspend fun deleteAllPosts()

    @Query("SELECT * FROM ${LocalArticle.TABLE_NAME} WHERE ID = :postId")
    fun getPostById(postId: Int): Flow<LocalArticle>

    @Query("SELECT * FROM ${LocalArticle.TABLE_NAME}")
    fun getAllArticles(): Flow<List<LocalArticle>>
}
