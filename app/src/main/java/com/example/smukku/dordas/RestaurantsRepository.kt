package com.example.smukku.dordas

import android.content.Context
import android.preference.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(val doordashApi: DoordashApi) {

    val favouriteKey = "favourite_"

    //Not handling errors for now, running short of time
    suspend fun getRestaurants(
        offset: Int, limit: Int = 10, latitude: Float = 37.422740f, longitude: Float = -122.139956f
    ): List<Restaurant>? {
        return withContext(Dispatchers.IO) {
            val response = doordashApi.getRestaurants(latitude, longitude, offset, limit).execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    fun saveFavoriteStatus(context: Context, id: Int, favourite: Boolean) {
        // I would usually get the preferences injected, but to save time, just writing the code here.
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean("$favouriteKey$id", favourite)
            .apply()
    }

    fun checkFavourite(context: Context, id: Int): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean("$favouriteKey$id", false)
    }

}