package com.example.evilbot.favorites.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evilbot.R
import kotlinx.android.synthetic.main.favorites_fragment.*

class FavoritesFragment : Fragment() {

    var favoritesAdapter: FavoritesAdapter? = null
    var favorite_insults = mutableListOf<String>("Jeg bryr meg ikke hva andre synes om deg, jeg synes du er kul.", "Det ser ut som du trenger litt søvn.")


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
        favorites_recyclerview.adapter = favoritesAdapter
        favorites_recyclerview.layoutManager= LinearLayoutManager(context) //(this) ??


        val item=object :SwipeToDelete(context, 0, ItemTouchHelper.LEFT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                favoritesAdapter!!.del(viewHolder.adapterPosition) //Er det lov å bruke !! egt? haha
            }
        }

        val itemTouchHelper=ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(favorites_recyclerview)

        //TODO: save_insultButton
    }

    /*val itemTouchHelperCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                val viewAdapter = favoritesAdapter
                (viewAdapter as FavoritesAdapter).deleteFavorite(viewHolder)
                Toast.makeText(requireContext(), "DELETED", Toast.LENGTH_LONG).show()
            }

        }*/
}