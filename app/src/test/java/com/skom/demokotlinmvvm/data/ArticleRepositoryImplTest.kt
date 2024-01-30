package com.skom.demokotlinmvvm.data

import com.skom.demokotlinmvvm.data.local.ArticleDao
import com.skom.demokotlinmvvm.data.remote.NetworkService
import com.skom.demokotlinmvvm.data.remote.RemoteArticle
import com.skom.demokotlinmvvm.domain.Article
import com.skom.demokotlinmvvm.domain.ArticleRepository
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class ArticleRepositoryTest {

    private val article1 = Article(1, "title1", "body1", "imageUrl1", "author1")
    private val article2 = Article(2, "title2", "body2", "imageUrl2", "author2")
    private val article3 = Article(3, "title3", "body3", "imageUrl3", "author3")

    private val articles = listOf(article1, article2, article3)

    private val articleDao = mockk<ArticleDao>()
    private val networkService = mockk<NetworkService>()

    private val repositoryImpl: ArticleRepository = ArticleRepositoryImpl(articleDao, networkService)


    @Test
    fun `getArticles should return list of articles`() = runBlocking {
        val remoteArticles = listOf(
            RemoteArticle(1, "title1", "body1", "imageUrl1", "author1"),
            RemoteArticle(2, "title2", "body2", "imageUrl2", "author2"),
            RemoteArticle(3, "title3", "body3", "imageUrl3", "author3")
        )
        val response = Response.success(remoteArticles)

        coEvery { networkService.getArticles() } returns response
        coEvery { articleDao.addArticles(response.body()!!.map { it.maptoLocalArticle() }) } returns Unit
        coEvery { articleDao.getAllArticles() } returns flow { emit(remoteArticles.map { it.maptoLocalArticle() }) }

        repositoryImpl.getArticles().collect { result ->
            assertEquals(articles, result)
        }
    }
}