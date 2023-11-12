package com.skom.demokotlinmvvm.domain

import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(): Flow<List<Article>>
}