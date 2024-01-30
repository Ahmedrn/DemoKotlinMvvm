package com.skom.demokotlinmvvm.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("/DummyFoodiumApi/api/posts/")
    suspend fun getArticles(): Response<List<RemoteArticle>>

    companion object {
        const val API_URL = "https://patilshreyas.github.io/"
    }
}