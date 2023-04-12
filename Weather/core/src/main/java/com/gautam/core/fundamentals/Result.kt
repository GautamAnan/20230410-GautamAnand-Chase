package com.gautam.core.fundamentals

/**
 * Result
 *
 * @param E
 * @param R
 * @constructor Create empty Result
 */
sealed class Result<out E, out R> {

    /**
     * Success
     *
     * @param R
     * @property data
     * @constructor Create empty Success
     */
    data class Success<out R>(val data: R) : Result<Nothing, R>()

    /**
     * Failure
     *
     * @param E
     * @property error
     * @constructor Create empty Failure
     */
    data class Failure<E>(val error: E) : Result<E, Nothing>()

    /**
     * Map result
     *
     * @param success
     * @param failure
     * @receiver
     * @receiver
     * @return
     */
    fun mapResult(success: (R) -> Any, failure: (E) -> Any) : Any =
        when (this) {
            is Success -> success(data)
            is Failure -> failure(error)
        }
}

