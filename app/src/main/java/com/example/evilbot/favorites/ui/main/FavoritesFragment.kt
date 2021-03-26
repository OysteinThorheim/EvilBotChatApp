package com.example.evilbot.favorites.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.evilbot.R
import kotlinx.android.synthetic.main.favorites_fragment.*

class FavoritesFragment : Fragment() {

    var favoritesAdapter: FavoritesAdapter? = null
    val favorite_insults = mutableListOf<String>("Jeg bryr meg ikke hva andre synes om deg, jeg synes du er kul", "Det ser ut som du trenger litt søvn")

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        // TODO: Use the ViewModel

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesAdapter = FavoritesAdapter(favorite_insults)

        favorites_recyclerview.adapter
    }
}