package com.skom.demokotlinmvvm.data

import com.skom.demokotlinmvvm.data.local.ArticleDao
import com.skom.demokotlinmvvm.data.remote.NetworkService
import com.skom.demokotlinmvvm.domain.Article
import com.skom.demokotlinmvvm.domain.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val networkService: NetworkService
) : ArticleRepository {

    override suspend fun getArticles(): Flow<List<Article>> {
        val response = networkService.getArticles()
        if (response.isSuccessful && response.body() != null) {
            articleDao.addPosts(response.body()!!.map { it.maptoLocalArticle() })
        } else {
            // Todo: Handle error
        }
        return articleDao.getAllPosts()
            .map { value -> value.map { localArticle -> localArticle.mapToArticle() } }
    }

}