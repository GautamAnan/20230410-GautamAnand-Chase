package com.gautam.core

import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface BaseUseCase<in P, out R> where R : Any {
    suspend fun execute(
        params: P,
        coroutineContext: CoroutineContext = Dispatchers.IO
    ): Result<Error, R> = withContext(coroutineContext) { execute(params) }
}
