package com.example.smukku.dordas

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RestaurantsAdapter(
    private val viewModel: MainViewModel,
    private var list: List<Restaurant> = listOf()
) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    fun setItems(restaurantsList: List<Restaurant>) {
        list = restaurantsList
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RestaurantViewHolder.create(parent, viewModel)

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int? = recyclerView.layoutManager?.childCount
                val totalVisibleItems: Int? = recyclerView.layoutManager?.itemCount
                val pastVisiblesItems: Int? = (recyclerView.layoutManager as? LinearLayoutManager)
                    ?.findFirstVisibleItemPosition()
                if (visibleItemCount == null || totalVisibleItems == null || pastVisiblesItems == null) return
                viewModel.firstVisibleItem = pastVisiblesItems
                if (visibleItemCount + pastVisiblesItems >= totalVisibleItems) {
                    viewModel.getRestaurants(list.size)
                }
            }
        })
    }
}