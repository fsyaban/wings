package com.fachrul.wings.data.auth

import java.util.*

interface AuthDbService {
    fun login()
    fun isUserLoggedIn():Boolean
}