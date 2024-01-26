package com.vaultspay.emallcomposeproject.data.api

import com.vaultspay.emallcomposeproject.domain.models.auth.signin.SigninResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("emailOrPhone") emailPhone: String,
        @Field("password") password: String
    ): Response<SigninResponse>

}