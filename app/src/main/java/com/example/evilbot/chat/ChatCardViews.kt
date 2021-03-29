package com.example.evilbot.chat

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.evilbot.R

class ChatCardViews(context: Context): ConstraintLayout(context) {

    //TODO: private val

    init {
       val view = LayoutInflater.from(context).inflate(R.layout.evilbot_chat_card,this)
    }

}
