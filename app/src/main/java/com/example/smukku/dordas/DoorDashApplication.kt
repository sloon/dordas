package com.example.smukku.dordas

import android.app.Application
import com.example.smukku.dordas.di.AppComponent
import com.example.smukku.dordas.di.DaggerAppComponent

class DoorDashApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}