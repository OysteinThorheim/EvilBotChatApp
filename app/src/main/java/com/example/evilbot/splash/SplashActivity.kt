package com.example.evilbot.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.evilbot.MainActivity
import com.example.evilbot.R
import com.example.evilbot.chat.ChatActivity
import com.example.evilbot.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    //private val splashViewModel: SplashViewModel by viewModels()
    val SPLASH_TIME_OUT = 3000L //Duration on splashActivity (3 sec)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({

            val userIsLoggedIn = false //TODO: lage kode for om bruker logget på eller ikke (sharedprefs)
            val activityIntent = if(userIsLoggedIn){
                Intent(this, ChatActivity::class.java)
            }else{
                Intent(this, LoginActivity::class.java)
            }
            activityIntent.flags = activityIntent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(activityIntent)

            finish()
        }, SPLASH_TIME_OUT)
    }
}