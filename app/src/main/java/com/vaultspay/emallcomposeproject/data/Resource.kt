package com.vaultspay.emallcomposeproject.data

import com.vaultspay.emallcomposeproject.domain.models.ResponseError


sealed class Resource<out T> {

    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: ResponseError) : Resource<T>()

    companion object {

        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> success(data: T) =
            Success(data)

        /**
         * Returns [State.Error] instance.
         * @param message Description of failure.
         */
        fun <T> error(error: ResponseError) = Error<T>(error)
    }


}