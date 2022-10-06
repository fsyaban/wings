package com.fachrul.wings.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.fachrul.wings.data.dao.LoginDao
import com.fachrul.wings.data.entity.LoginEntity
import com.fachrul.wings.data.entity.Result
import com.fachrul.wings.utils.AppExecutors

class LoginRepository(
    private val loginDao:LoginDao,
    private val appExecutors: AppExecutors
) {

    private val resultLogin = MediatorLiveData<Result<Boolean>>()

    fun getUser(user:LoginEntity): LiveData<Result<Boolean>> {
        val localdata = loginDao.getUser(user.user)
        resultLogin.addSource(localdata){
            when{
                it == null -> resultLogin.value = Result.Error("User tidak ditemukan")
                it.password!=user.password -> resultLogin.value = Result.Error("invalid password")
                else -> resultLogin .value = Result.Success(true)
            }
        }
        return  resultLogin
    }

    fun insertUser(user:LoginEntity){
        appExecutors.diskIO.execute {
            loginDao.registerUser(user)
        }
    }
}