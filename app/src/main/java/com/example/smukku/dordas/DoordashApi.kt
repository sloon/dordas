package com.example.smukku.dordas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DoordashApi {
    @GET("v2/restaurant")
    fun getRestaurants(
        @Query("lat") latitude: Float,
        @Query("lng") longitude: Float,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<Restaurant>>
}