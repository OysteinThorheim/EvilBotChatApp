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


    fun goToTwistFragment() {
        //TODO: notat til Joey
        //TODO: høre med øivind: når en går til en annen side, er det altid en container man går fra? "R.id.login_container,TwistFragment()"
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_container, TwistFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

}