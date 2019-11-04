package com.example.smukku.dordas.di

import com.example.smukku.dordas.DoordashApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.doordash.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun getDoordashApi(retrofit: Retrofit): DoordashApi = retrofit.create(DoordashApi::class.java)
}