package com.skom.demokotlinmvvm.data.remote

//
//sealed class NetworkResult<T : Any> {
//    class Success<T : Any>(val data: T) : NetworkResult<T>()
//    class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
//    class Exception<T : Any>(val e: Throwable) : NetworkResult<T>()
//}


/* WITH Kotlin 1.5 */
sealed interface NetworkResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : NetworkResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>
class ApiException<T : Any>(val e: Throwable) : NetworkResult<T>
