package com.example.evilbot.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.evilbot.R
import com.example.evilbot.chat.ChatActivity

class TwistFragment : Fragment() {

    //TODO: hva er denne til ? høre med øivind
    companion object {
        fun newInstance() = TwistFragment()
    }

    private lateinit var viewModel: TwistViewModel
    private lateinit var askMeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.twist_fragment, container, false)

        askMeButton = view.findViewById(R.id.ask_me_button)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwistViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        askMeButton.setOnClickListener {

            val intent = Intent(activity, ChatActivity::class.java)
            intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)

        }



    }
}