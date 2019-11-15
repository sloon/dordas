package com.example.smukku.dordas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantViewHolder(view: View, private val viewModel: MainViewModel) : RecyclerView.ViewHolder(view) {

    fun bind(restaurant: Restaurant) {
        itemView.favSwitch.setOnCheckedChangeListener(null)
        itemView.businessName.text = restaurant.business?.name
        itemView.description.text = restaurant.description
        itemView.status.text = restaurant.status
        Glide.with(itemView.context).load(restaurant.coverUrl).into(itemView.coverImage)
        //set on click listener for the view

        restaurant.id?.let {
            itemView.favSwitch.isChecked = viewModel.checkFavourite(itemView.context, it)
        }

        itemView.favSwitch.setOnCheckedChangeListener { compoundButton, b ->
            restaurant.id?.let {
                viewModel.saveFavourite(itemView.context, it, b)
            }
        }

    }

    companion object {
        fun create(parent: ViewGroup, viewModel: MainViewModel) = RestaurantViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_restaurant, parent, false), viewModel
        )
    }
}