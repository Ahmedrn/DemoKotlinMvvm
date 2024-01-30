package com.skom.demokotlinmvvm.data

import com.skom.demokotlinmvvm.data.local.ArticleDao
import com.skom.demokotlinmvvm.data.remote.ApiError
import com.skom.demokotlinmvvm.data.remote.ApiException
import com.skom.demokotlinmvvm.data.remote.ApiSuccess
import com.skom.demokotlinmvvm.data.remote.NetworkDataSource
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
//        val response = networkService.getArticles()
//        if (response.isSuccessful && response.body() != null) {
//            articleDao.addArticles(response.body()!!.map { it.maptoLocalArticle() })
//        } else {
//            // Todo: Handle error
//        }
        val networkDataSouce = NetworkDataSource(networkService)
        when (val response = networkDataSouce()) {
            is ApiSuccess -> articleDao.addArticles(response.data.map { it.maptoLocalArticle() })
            is ApiError -> println("Error: ${response.message}")
            is ApiException -> try {
                throw response.e
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return articleDao.getAllArticles()
            .map { value -> value.map { localArticle -> localArticle.mapToArticle() } }
    }

}
