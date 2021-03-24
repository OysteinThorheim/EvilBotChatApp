package com.example.evilbot.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evilbot.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction()
            .replace(R.id.login_container, LoginFragment()).commitNow()

    }
}