package com.example.smukku.dordas

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(val doordashApi: DoordashApi) {

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
}