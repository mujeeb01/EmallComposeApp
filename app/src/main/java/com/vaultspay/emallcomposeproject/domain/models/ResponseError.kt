package com.vaultspay.emallcomposeproject.domain.models

import com.google.gson.annotations.SerializedName

data class ResponseError(
    val code: Int?,
    val message: String?,
    val data: ErrorData?,
) {
    data class ErrorData(
        @SerializedName("errors")
        val errorsList: ArrayList<ErrorsList>,
    ) {
        data class ErrorsList(
            @SerializedName("path")
            val path: String,
            @SerializedName("field")
            val field: String,
            @SerializedName("message")
            val message: String
        )

    }
}