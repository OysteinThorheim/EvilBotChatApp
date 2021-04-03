package com.example.evilbot.chat

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class  ChatViewModel : ViewModel() {


 /*   fun getInsults(context: Context, successCallBack: (List<ChatObject>) -> Unit, errorCallBack: () -> Unit) {
        val gson = Gson()
        val queue = Volley.newRequestQueue(context)
        val url = "https://evilinsult.com/generate_insult.php?lang=en&type=json"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                //denne kjører når det går bra.
                val listType: Type = object : TypeToken<List<ChatObject?>?>() {}.type
                val insults = gson.fromJson<List<ChatObject>>(response, listType) //TODO HVORFOR VIL DU IKKE FUNGERE!!!! FEILMELDING -> Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $

                successCallBack(insults)
            },
            {
                //denne kjører når det ikke går bra.
                errorCallBack()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)


    }*/



    val insultList = listOf<String>(
        "https://evilinsult.com/generate_insult.php?lang=en&type=json"
    )

    fun getInsult(context: Context?, insultInterface: InsultInterface, /*currentInsultCounter: String*/){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = insultList.toString()/*[currentInsultCounter.toInt()]*/

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->

                val result: ChatObject? = Klaxon().parse<ChatObject>(response)

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

}