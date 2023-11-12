package com.skom.demokotlinmvvm.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<out T, in Params> constructor(
    private val threadExecutor: CoroutineDispatcher = Dispatchers.IO
) {
    protected abstract fun execute(params: Params? = null): Flow<T>

    open operator fun invoke(params: Params? = null): Flow<T> =
        execute(params).flowOn(threadExecutor)

}