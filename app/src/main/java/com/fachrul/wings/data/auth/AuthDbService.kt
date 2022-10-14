package com.fachrul.wings.data.auth

import com.fachrul.wings.data.entity.LoginEntity
import java.util.*

interface AuthDbService {
    fun login(loginEntity:LoginEntity)
    fun isUserLoggedIn():Boolean
    fun getUser():String
}