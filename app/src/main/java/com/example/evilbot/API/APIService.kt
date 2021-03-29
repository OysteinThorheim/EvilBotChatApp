package com.example.evilbot.API

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon

/*class APIService {

    val insultList = listOf<String>(
        "https://evilinsult.com/generate_insult.php?lang=en&type=json"
    )

    fun getInsult(context: Context, insultInterface: InsultInterface, currentInsultIndex: Int){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = insultList[currentInsultIndex]

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->

                val result: Insult? = Klaxon().parse<Insult>(response)

                result?.let {
                    Log.d("LOG_MESSAGE", it.insult)
                    insultInterface.onInsultReceived(it)
                }
            },
            { Log.d("LOG_MESSAGE", "That didn't work!")
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}*/