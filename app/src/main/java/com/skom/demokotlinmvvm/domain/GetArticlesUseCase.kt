package com.skom.demokotlinmvvm.domain

import com.skom.demokotlinmvvm.data.ArticleRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articleRepository: ArticleRepositoryImpl) {

    suspend operator fun invoke(): Flow<List<Article>> {
        return withContext(Dispatchers.IO) {
            articleRepository.getArticles()
        }
    }

}