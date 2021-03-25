package com.example.evilbot.chat

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import com.example.evilbot.R
import com.example.evilbot.favorites.FavoritesActivity
import com.example.evilbot.favorites.ui.main.FavoritesFragment
import com.example.evilbot.login.LoginActivity
import com.example.evilbot.login.TwistFragment
import kotlinx.android.synthetic.main.favorites_fragment.*

class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private lateinit var viewModel: ChatViewModel
    private lateinit var burgerWindow: ConstraintLayout
    private lateinit var favoritesButton: AppCompatButton
    private lateinit var signOutButton: AppCompatButton
    private lateinit var icBurger: ImageButton
    private lateinit var sendMessageButton: ImageButton //TODO: sende melding funksjon


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


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListeners()

    }

    private fun setButtonListeners() {

        icBurger.setOnClickListener {
            burgerWindow.isVisible = !burgerWindow.isVisible
        }

        favoritesButton.setOnClickListener {


            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.chat_container, FavoritesFragment())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()

            //val intent = Intent(activity, FavoritesActivity::class.java)
            //intent.flags = intent.flags
            //startActivity(intent)

        }


        //TODO: SharedPrefs implementation
        signOutButton.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
        }

    }


}