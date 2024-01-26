package com.vaultspay.emallcomposeproject.data.repositoryImpl.auth

import com.vaultspay.emallcomposeproject.data.api.ApiService
import com.vaultspay.emallcomposeproject.domain.models.auth.signin.SigninResponse
import com.vaultspay.emallcomposeproject.domain.repositories.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    /*override suspend fun loginRequest(emailPhone: String, password: String) {
        flow {
            emit(apiService.login(emailPhone, password))
        }
    }*/
    override suspend fun loginRequest(
        emailPhone: String,
        password: String
    ): Flow<Response<SigninResponse>> {
       return flow {
            emit(apiService.login(emailPhone, password))
        }
    }
}