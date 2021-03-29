package com.example.evilbot.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.evilbot.R

class ChatAdapter(
    var dataSet: List<String>
): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

       return ChatViewHolder(ChatCardViews(parent.context))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
       //TODO: holder.view.text = dataSet[position]

    }


    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ChatViewHolder(val view: ChatCardViews): RecyclerView.ViewHolder(view)

}
