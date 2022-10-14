package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fachrul.wings.data.auth.AuthDbService
import com.fachrul.wings.data.entity.LoginEntity
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.data.entity.Result
import com.fachrul.wings.data.repository.LoginRepository
import com.fachrul.wings.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val loginRepository: LoginRepository,
    private val authDbService: AuthDbService
) : AndroidViewModel(application) {

    var loginState : LiveData<Result<Boolean>>?= null
    val isLoggedIn = authDbService.isUserLoggedIn()


    fun login(loginEntity: LoginEntity){
        viewModelScope.launch {
            loginState = loginRepository.getUser(loginEntity)
        }
    }





}