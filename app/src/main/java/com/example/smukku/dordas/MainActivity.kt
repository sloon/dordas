package com.example.smukku.dordas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val adapter = RestaurantsAdapter(viewModel)

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.adapter = adapter
        viewModel.restaurantsLiveData.observe(this, Observer { adapter.setItems(it) })

        //Fetch list for first time when firstVisibleItem is null,
        //just scroll to previous position if list was already populated and scrolled
        viewModel.firstVisibleItem?.let {
            //Take users to where they were
            list.scrollToPosition(it)
        } ?: run {
            viewModel.getRestaurants(0)
        }
    }
}
