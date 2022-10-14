package com.fachrul.wings.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fachrul.wings.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}