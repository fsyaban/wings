package com.fachrul.wings.data.auth

import android.content.Context
import android.preference.PreferenceManager
import com.fachrul.wings.data.entity.LoginEntity

class AuthDbServicePreference(val context: Context) : AuthDbService {

    val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun login(loginEntity: LoginEntity) {
        preferences.edit().putBoolean(IS_USER_LOGGED_IN, true).putString(USER, loginEntity.user)
            .apply()
    }



    override fun isUserLoggedIn(): Boolean =
        preferences.getBoolean(IS_USER_LOGGED_IN, false)

    override fun getUser(): String = preferences.getString(USER,"")?:""


    companion object Constants {
        const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
        const val USER = "USER"
    }
}