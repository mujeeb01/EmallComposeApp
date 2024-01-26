package com.vaultspay.emallcomposeproject.domain.repositories.auth

import com.vaultspay.emallcomposeproject.domain.models.auth.signin.SigninResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface AuthRepository {
    suspend fun loginRequest(emailPhone: String, password: String) : Flow<Response<SigninResponse>>
}