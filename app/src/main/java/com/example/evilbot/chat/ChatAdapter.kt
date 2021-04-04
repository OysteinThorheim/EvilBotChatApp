package com.example.evilbot.chat

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evilbot.utils.Constants.RECEIVE_ID
import com.example.evilbot.utils.Constants.SEND_ID
import com.example.evilbot.R
import kotlinx.android.synthetic.main.evilbot_chat_card.view.*

class ChatAdapter(var dataSet: MutableList<ChatObject>,var chatName: String) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                dataSet.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.evilbot_chat_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        val currentMessage = dataSet[position]
        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.user_name_tv.text = chatName
                holder.itemView.user_tv.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.card_evilbot.visibility = View.GONE
            }

            RECEIVE_ID -> {
                holder.itemView.evil_bot_tv.apply {
                    text = currentMessage.insult
                    visibility = View.VISIBLE
                }
                holder.itemView.card_user.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun insertMessage(message: ChatObject) {
        this.dataSet.add(message)
        notifyItemInserted(dataSet.size)
    }
    /*
    fun saveInsult(SaveInsult: String){
        val editor = sharedPrefs.edit()
        val insult = sharedPrefs.getStringSet(getString(R.), setOf())?.toMutableList()

        insult?.add(SaveInsult)
        editor.putStringSet(getString(R.string.shared_prefs_favorite_insult), insult?.toSet())
        editor.commit()


    }*/

}


/*
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
}*/
