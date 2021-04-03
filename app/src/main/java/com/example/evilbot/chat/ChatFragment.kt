package com.example.evilbot.chat

import android.content.Context
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
import com.example.evilbot.SHARED_PREFS_NAME
import com.example.evilbot.SHARED_PREFS_TEXT
import com.example.evilbot.utils.Constants
import com.example.evilbot.utils.Constants.RECEIVE_ID
import kotlinx.android.synthetic.main.evilbot_chat_card.*

class ChatFragment : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private lateinit var burgerWindow: ConstraintLayout
    private lateinit var favoritesButton: AppCompatButton
    private lateinit var signOutButton: AppCompatButton
    private lateinit var icBurger: ImageButton
    private lateinit var ChatInputField: EditText
    private lateinit var sendMessageButton: ImageButton //TODO: sende melding funksjon
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter


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
        ChatInputField = view.findViewById(R.id.chat_input_editText)
        sendMessageButton = view.findViewById(R.id.send_message_button)


        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListeners()
        recyclerView()


        /*   viewModel.getInsults(requireContext(), //TODO tilhører Gson i ChatViewmodel
               { insults ->

               },
               {
                   Toast.makeText(
                       context,
                       "Could not get insult quote, please try again later.",
                       Toast.LENGTH_LONG
                   ).show()
               }
           )*/
    }


    //LayoutManager defines how the recyclerView should look like.
    private fun recyclerView() {
        adapter = ChatAdapter(
            mutableListOf()
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        /*  chatAdapter = ChatAdapter(
              listOf(
                  ChatObject(1,"dldasdsao"),
                  ChatObject(2,"skaomcsdjocknm"),
                  ChatObject(3,"ijuvhfin"),
                  ChatObject(4,"njfeivbhiufijosmcs"),
                  ChatObject(5,"jidksnckslmpkxaslømxslmxalksmxlkam"),
                  ChatObject(6,"123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789"),
                  ChatObject(6,"vmkfomoksmdpkmwl,mclw,emløwe,ølmwelømølmwelømlekwmceklwmdklwemølmwelømlekwmceklwmdklwemølmwelømlekwmceklwmdklwemlekwmceklwmdklwem"),
                  ChatObject(6,"vmkfomoksmdpkmwl,mclw,emløwe,ølmwelømlekwmceklwmdklwem"),
                  ChatObject(6,"vmkfomoksmdpkmwl,mclw,emløwe,ølmwelømlekwmceklwmdklwemdewiodjeowjdoiwejdjewondjewnd"),
              )
          )
          recyclerView.adapter = chatAdapter

         */
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
            val message = ChatInputField.text.toString()

/*
            val sharedPreferences =
                requireActivity().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
            val chatName = sharedPreferences.getString(SHARED_PREFS_NAME, null)
            if (sharedPreferences.contains(SHARED_PREFS_TEXT)) {

                user_name_tv.text = chatName
            }*/



            if (message.isNotEmpty()) {
                ChatInputField.setText("")

                adapter.insertMessage(ChatObject("1", "dw", message, SEND_ID))
                recyclerView.scrollToPosition(adapter.itemCount - 1)

            }
            botResponds() //TODO: denne funksjonen må lages så boten vår svarer på bruker når bruker har sendt en melding (kan svare med egendefinerte meldinger vi lager og fra api)
        }

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
        //val currentInsultCounter = "0"
        val apiService = ChatViewModel()
        val message = ChatInputField.text.toString()
        val insult =
            ChatObject(number = "1"/*currentInsultCounter*/, "wdqawdwadadwadawd", message, RECEIVE_ID)
        //if (message.isNotEmpty()) {
        //  ChatInputField.setText("")

        adapter.insertMessage(insult)
        recyclerView.scrollToPosition(adapter.itemCount - 1)
        apiService.getInsult(context, object : InsultInterface {
            override fun onInsultReceived(insult: ChatObject) {
                evil_bot_tv.text = insult.insult
            }
        }/*, currentInsultCounter*/)

    }
}


