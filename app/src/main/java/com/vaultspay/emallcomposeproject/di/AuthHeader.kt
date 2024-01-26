package com.vaultspay.emallcomposeproject.di

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthHeader : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request = original.newBuilder()
            .header("app","mobile")
            .header("appType","android")
            .header("Accept-Language","en")//todo update language
            .header("appVersion", "1.0")
            .header("firebase_device_id", "")//todo
            //.header("Authorization", "${MainSingleTone.userData?.token_type} ${MainSingleTone.userData?.access_token}")
            //.header("firebase_device_id", MainSingleTone.firebaseToken)
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}