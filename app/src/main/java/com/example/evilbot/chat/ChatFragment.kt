package com.example.evilbot.chat

import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evilbot.utils.Constants.SEND_ID
import com.example.evilbot.R
import com.example.evilbot.SHARED_PREFS_FAVORITE_INSULT
import com.example.evilbot.SHARED_PREFS_NAME
import com.example.evilbot.SHARED_PREFS_SAVED_INSULT
import com.example.evilbot.favorites.ui.main.FavoritesAdapter

import com.example.evilbot.utils.BotResponse
import com.example.evilbot.utils.Constants
import com.example.evilbot.utils.Constants.RECEIVE_ID
import kotlinx.android.synthetic.main.evilbot_chat_card.*
import kotlinx.coroutines.delay

class ChatFragment : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private lateinit var burgerWindow: ConstraintLayout
    private lateinit var favoritesButton: AppCompatButton
    private lateinit var signOutButton: AppCompatButton
    private lateinit var icBurger: ImageButton
    private lateinit var chatInputField: EditText
    private lateinit var sendMessageButton: ImageButton //TODO: sende melding funksjon
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter
    private lateinit var saveInsultButton: ImageButton
   // private lateinit var sharedPrefs: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)

        favoritesButton = view.findViewById(R.id.favorites_button)
        signOutButton = view.findViewById(R.id.signOut_button)
        icBurger = view.findViewById(R.id.ic_burger)
        burgerWindow = view.findViewById(R.id.burger_window)
        burgerWindow.isVisible = false
        signOutButton = view.findViewById(R.id.signOut_button)
        recyclerView = view.findViewById(R.id.chat_recyclerView)
        chatInputField = view.findViewById(R.id.chat_input_editText)
        sendMessageButton = view.findViewById(R.id.send_message_button)
      //  saveInsultButton = view.findViewById(R.id.save_insult_button)


        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListeners()
        recyclerView()

    }


    //LayoutManager defines how the recyclerView should look like.
    private fun recyclerView() {

        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val chatName = sharedPreferences.getString(SHARED_PREFS_NAME, "Øivind")

        adapter = ChatAdapter(
            mutableListOf(),
            chatName ?: "Øyvind"
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


    }

    private fun setButtonListeners() {

        icBurger.setOnClickListener {
            burgerWindow.isGone = !burgerWindow.isGone
        }

        favoritesButton.setOnClickListener {
            (activity as ChatActivity).goToFavorites()
        }

        //TODO: SharedPrefs implementation
        signOutButton.setOnClickListener {
            (activity as ChatActivity).logUserOut()
        }

        sendMessageButton.setOnClickListener {
            val message = chatInputField.text.toString()

            if (message.isNotEmpty()) {
                chatInputField.setText("")

                adapter.insertMessage(ChatObject("1", "dw", message, SEND_ID))
                recyclerView.scrollToPosition(adapter.itemCount - 1)
                if (message.contains("?")) {
                    botResponds()
                } else {
                    val botCustomResponse = BotResponse.preSetResponses(message)
                    val chatObject = ChatObject("1", botCustomResponse, "", RECEIVE_ID)
                    adapter.insertMessage(chatObject)
                    recyclerView.scrollToPosition(adapter.itemCount - 1)

                }
            }
            //TODO: denne funksjonen må lages så boten vår svarer på bruker når bruker har sendt en melding (kan svare med egendefinerte meldinger vi lager og fra api)
        }
/*
        saveInsultButton.setOnClickListener {
            viewModel.getInsult()
            val sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS_FAVORITE_INSULT,Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putStringSet(SHARED_PREFS_SAVED_INSULT,FavoritesAdapter().favorite_list)

        }
*/
    }

/*    private fun botRespond(message: String) {

        GlobalScope.launch {
            //fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {

                val response = BotResponse.preSetResponses(message)

                adapter.insertMessage(ChatObject("1", "insult", response, RECEIVE_ID))
                recyclerView.scrollToPosition(adapter.itemCount - 1)

            }
        }
    }*/

    fun botResponds() {
        val answer = object : InsultInterface {

            override fun onInsultReceived(insult: ChatObject) {
                adapter.insertMessage(insult)
                recyclerView.scrollToPosition(adapter.itemCount - 1)
            }
        }

        viewModel.getInsult(context, answer)

    }
}


