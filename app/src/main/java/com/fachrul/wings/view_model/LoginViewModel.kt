package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fachrul.wings.data.entity.LoginEntity
import com.fachrul.wings.data.entity.Result
import com.fachrul.wings.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    val loginRepository: LoginRepository
) : AndroidViewModel(application) {

    var loginState : LiveData<Result<Boolean>>?= null


    fun login(loginEntity: LoginEntity){
        viewModelScope.launch {
            loginState = loginRepository.getUser(loginEntity)
        }
    }



}