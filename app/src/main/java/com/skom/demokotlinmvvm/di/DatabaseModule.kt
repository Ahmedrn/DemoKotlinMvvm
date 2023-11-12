package com.skom.demokotlinmvvm.di

import android.content.Context
import com.skom.demokotlinmvvm.data.local.ArticleDao
import com.skom.demokotlinmvvm.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideArticleDao(appDatabase: ArticleDatabase): ArticleDao {
        return appDatabase.getArticleDao()
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        return ArticleDatabase.getInstance(appContext)
    }
}