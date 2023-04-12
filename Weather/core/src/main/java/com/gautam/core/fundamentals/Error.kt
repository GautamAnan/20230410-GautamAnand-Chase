package com.gautam.core.fundamentals

import com.gautam.core.fundamentals.Constants.EMPTY_STRING
import com.gautam.core.fundamentals.Constants.VALUE_ZERO

sealed class Error {
    data class RemoteError(val code: Int = VALUE_ZERO, val message: String = EMPTY_STRING) : Error()
    data class DBError(val error: String = EMPTY_STRING) : Error()
    data class GenericError(val message: String = EMPTY_STRING) : Error()
    data class RemotePostError(
        val status: Int = VALUE_ZERO,
        val error: String = EMPTY_STRING
    ) : Error()
}