package com.skom.demokotlinmvvm.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articleRepository: ArticleRepository) :
    BaseUseCase<List<Article>>() {

    override suspend fun execute(): Flow<List<Article>> {
        return articleRepository.getArticles()
    }


}