package com.skom.demokotlinmvvm.di

import com.skom.demokotlinmvvm.data.ArticleRepositoryImpl
import com.skom.demokotlinmvvm.domain.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ArticleRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindArticleRepository(repository: ArticleRepositoryImpl): ArticleRepository

}