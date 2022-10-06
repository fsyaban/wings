package com.fachrul.wings.data.auth

import android.content.Context
import android.preference.PreferenceManager

class AuthDbServicePreference(val context: Context) : AuthDbService {

    val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun login() {
        preferences.edit().putBoolean(IS_USER_LOGGED_IN, true).apply()
    }

    override fun isUserLoggedIn():Boolean =
        preferences.getBoolean(IS_USER_LOGGED_IN,false)


    companion object Constants {
        const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
    }
}