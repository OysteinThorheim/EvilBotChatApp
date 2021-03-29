package com.example.evilbot.chat

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    var dataSet: List<ChatObject>
): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = CustomChatBubbleView(parent.context)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

       return ChatViewHolder(CustomChatBubbleView(parent.context))

    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatObject = dataSet[position]
        holder.view.setChatText(chatObject.insult)


    }


    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ChatViewHolder(val view: CustomChatBubbleView): RecyclerView.ViewHolder(view)
}
