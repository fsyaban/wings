package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fachrul.wings.data.entity.LoginEntity
import com.fachrul.wings.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(application: Application, private val loginRepository: LoginRepository) :
    AndroidViewModel(application) {

    fun register(loginEntity: LoginEntity) {
        loginRepository.insertUser(loginEntity)
    }
}