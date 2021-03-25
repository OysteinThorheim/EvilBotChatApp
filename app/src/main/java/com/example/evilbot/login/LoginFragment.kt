package com.example.evilbot.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.example.evilbot.R

class LoginFragment : Fragment() {

    //TODO: hva er denne til ? høre med øivind
    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var submitButton: Button
    private lateinit var loginPageAnimation: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        submitButton = view.findViewById(R.id.submit_button)
        loginPageAnimation = view.findViewById(R.id.login_figure)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        submitButton.setOnClickListener {
            (activity as LoginActivity).goToTwistFragment()
        }
    }

    override fun onPause() {
        super.onPause()

        //lotti animation stop
        cancelAnimation()
    }

    override fun onResume() {
        super.onResume()

        //:TODO resume lotti animation (??)
        loginPageAnimation.isAnimating
    }


    private fun cancelAnimation() {
        loginPageAnimation.cancelAnimation()
    }

}