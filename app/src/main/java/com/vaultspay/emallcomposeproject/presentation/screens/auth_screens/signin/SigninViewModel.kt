package com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaultspay.emallcomposeproject.data.Resource
import com.vaultspay.emallcomposeproject.domain.models.ResponseError
import com.vaultspay.emallcomposeproject.domain.models.auth.signin.SigninResponse
import com.vaultspay.emallcomposeproject.domain.repositories.auth.AuthRepository
import com.vaultspay.emallcomposeproject.utils.extractErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    /*private val _loginRequest = SingleLiveEvent<Resource<SigninResponse>>()
    val loginRequest: LiveData<Resource<SigninResponse>>
        get() = _loginRequest*/

    private val _loginRequest: MutableStateFlow<Resource<SigninResponse>?> = MutableStateFlow(null)
    val loginRequest: StateFlow<Resource<SigninResponse>?> = _loginRequest

    fun loginRequest(email:String, password:String){
        viewModelScope.launch {
            //if (networkHelper.isNetworkConnected()) {
                _loginRequest.value = Resource.loading()
                authRepository.loginRequest(email, password).catch {
                   // _loginRequest.postValue(Resource.error(ResponseError(null,it.message?:"", null)))
                    _loginRequest.value = Resource.error(ResponseError(null,it.message?:"", null))
                }.collect{
                    if (it.isSuccessful){
                        it.body()?.let {
                            it.data?.let { data ->
                                if (data.emailVerficationStatus == 1 && data.phoneVerficationStatus == 1){
                                    //dataStoreProvider.storeUserInfo(data)
                                }
                            }
                            //_loginRequest.postValue(Resource.success(it))
                            _loginRequest.value = Resource.success(it)
                        } ?: run {
                            /*_loginRequest.postValue(
                                Resource.error(ResponseError(it.code(),it.message(), null))
                            )*/
                            _loginRequest.value =
                                Resource.error(ResponseError(it.code(),it.message(), null))

                        }
                    } else if(it.code() == 400 || it.code() == 401){//error code
                        val errorMessage = it.errorBody()!!.source().buffer.readUtf8()
                        //_loginRequest.postValue(Resource.error(errorMessage.extractErrorMessage()))
                        _loginRequest.value = Resource.error(errorMessage.extractErrorMessage())
                    }else{
                        /*_loginRequest.postValue(
                            Resource.error(
                                ResponseError(it.code(), "some thing went wrong", null)
                            )
                        )*/
                        _loginRequest.value =
                            Resource.error(
                                ResponseError(it.code(), "some thing went wrong", null)
                            )
                    }
                }
            /*} else {
                _loginRequest.postValue(
                    Resource.error(
                        ResponseError(null, "no internet connection", null)
                    )
                )
            }*/
        }
    }

}