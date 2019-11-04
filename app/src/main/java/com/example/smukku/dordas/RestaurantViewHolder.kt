package com.example.smukku.dordas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(restaurant: Restaurant) {
        itemView.businessName.text = restaurant.business?.name
        itemView.description.text = restaurant.description
        itemView.status.text = restaurant.status
        Glide.with(itemView.context).load(restaurant.coverUrl).into(itemView.coverImage)
    }

    companion object {
        fun create(parent: ViewGroup) = RestaurantViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_restaurant, parent, false)
        )
    }
}