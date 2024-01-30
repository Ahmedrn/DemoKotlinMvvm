package com.skom.demokotlinmvvm.presentation

import com.skom.demokotlinmvvm.domain.Article
import com.skom.demokotlinmvvm.domain.GetArticlesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    private val mockGetArticlesUseCase = mockk<GetArticlesUseCase>()
    private val viewModel = MainViewModel(mockGetArticlesUseCase)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getArticles updates articlesFlow`() = runBlockingTest {
        // Arrange
        val articles = listOf(Article(1, "title1", "body1", "imageUrl1", "author1"))
        coEvery { mockGetArticlesUseCase() } returns flowOf(articles)

        // Act
        viewModel.getArticles()

        // Assert
        assertEquals(articles, viewModel.articles.value)
    }
}