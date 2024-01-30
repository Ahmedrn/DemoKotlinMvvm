package com.skom.demokotlinmvvm.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetArticlesUseCaseTest {

    private val mockArticleRepository = mockk<ArticleRepository>()
    private val getArticlesUseCase = GetArticlesUseCase(mockArticleRepository)

    @Test
    fun `getArticlesUseCase only invokes ArticleRepository only once`() = runTest(UnconfinedTestDispatcher()) {

        // Given
        val articles = listOf(Article(1,"Test","Test","Test","Test"), Article(2,"Test","Test","Test","Test"))
        coEvery { mockArticleRepository.getArticles() } returns flowOf(articles)

        // When
        val result = getArticlesUseCase().first()

        // Then
        assertEquals(articles, result)
        coVerify(exactly = 1) { mockArticleRepository.getArticles() }
    }
}