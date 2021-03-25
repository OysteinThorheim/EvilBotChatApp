package com.example.evilbot.API

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evilbot.R
import kotlinx.android.synthetic.main.activity_a_p_i.*

class APIActivity : AppCompatActivity() {

    var currentInsultCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_p_i)

        button_TW.setOnClickListener {
            val apiService = APIService()
            apiService.getInsult(this, object: InsultInterface {
                override fun onInsultReceived(insult: Insult) {
                    API_TW.text = insult.insult
                }
            }, currentInsultCounter)
        }
    }
}