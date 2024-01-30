package com.skom.demokotlinmvvm.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<out T> {

    private val threadExecutor: CoroutineDispatcher = Dispatchers.IO
    
    protected abstract suspend fun execute(): Flow<T>

    suspend operator fun invoke(): Flow<T> = execute().flowOn(threadExecutor)

}