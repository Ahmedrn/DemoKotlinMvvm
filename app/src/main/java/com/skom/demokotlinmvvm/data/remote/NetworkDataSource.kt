package com.skom.demokotlinmvvm.data.remote

class NetworkDataSource (
    private val networkService: NetworkService
) {
    suspend operator fun invoke(): NetworkResult<List<RemoteArticle>> =
        handleApi { networkService.getArticles() }
}