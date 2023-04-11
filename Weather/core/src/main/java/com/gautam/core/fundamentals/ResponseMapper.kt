package com.gautam.core.fundamentals

import retrofit2.Response


interface ResponseMapper {
    suspend fun <E, R> execute(
        service: suspend () -> Response<E>,
        success: (E, Int) -> R,
        failure: (Int) -> Error = { _ -> Error.RemoteError() }
    ) = try {
        service().mapResult(failure = { Result.Failure(failure(it.code())) },
            success = { Result.Success(success(it.body()!!, it.code())) })
    } catch (exception: Exception) {
        Result.Failure(Error.GenericError(message = exception.message.toString()))
    }

    suspend fun <E, R> executeWithErrorMessageResponse(
        service: suspend () -> Response<E>,
        success: (E, Int) -> R,
        failure: (Int, String) -> Error = { status, error -> Error.RemoteError(status, error) }
    ) = try {
        service().mapResult(failure = { Result.Failure(failure(it.code(), it.message())) },
            success = { Result.Success(success(it.body()!!, it.code())) })
    } catch (exception: Exception) {
        Result.Failure(Error.GenericError(message = exception.message.toString()))
    }

    private fun <E, R> Response<E>.mapResult(
        failure: (Response<E>) -> Result.Failure<Error>, success: (Response<E>) -> Result.Success<R>
    ) = if (this.isSuccessful) {
        success(this)
    } else {
        failure(this)
    }
}