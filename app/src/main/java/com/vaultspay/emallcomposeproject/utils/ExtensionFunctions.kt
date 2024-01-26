package com.vaultspay.emallcomposeproject.utils

import com.google.gson.Gson
import com.vaultspay.emallcomposeproject.domain.models.ResponseError

val gsonObj = Gson()

fun String.extractErrorMessage(): ResponseError {
    gsonObj.serializeNulls()
    val errorObj =
        try {
            gsonObj.fromJson(this, ResponseError::class.java)
        } catch (e: Exception) {
            ResponseError(500, e.message, null)
        }
    return errorObj
}