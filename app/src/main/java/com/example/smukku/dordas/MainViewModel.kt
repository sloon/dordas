package com.example.smukku.dordas

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel (application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var restaurantsRepository: RestaurantsRepository

    //Used to persist list position during config changes
    var firstVisibleItem: Int? = null


    private val _restaurantsLiveData = MutableLiveData<MutableList<Restaurant>>()
    val restaurantsLiveData: LiveData<MutableList<Restaurant>> = _restaurantsLiveData

    init {
        (application as DoorDashApplication).appComponent.inject(this)
    }

    fun getRestaurants(offset: Int) {
        viewModelScope.launch {
            restaurantsRepository.getRestaurants(offset)?.let {
                _restaurantsLiveData.postValue(
                    _restaurantsLiveData.value?.apply {
                        addAll(it)
                    } ?: run {
                        it.toMutableList()
                    }
                )
            }
        }
    }

    fun saveFavourite(context: Context, id: Int, favourite: Boolean) {
        restaurantsRepository.saveFavoriteStatus(context, id, favourite)
    }

    fun checkFavourite(context: Context, id: Int) = restaurantsRepository.checkFavourite(context, id)

}