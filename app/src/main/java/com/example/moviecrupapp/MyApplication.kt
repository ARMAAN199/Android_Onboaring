package com.example.moviecrupapp

import android.app.Application
import com.example.moviecrupapp.Components.DComponent
import com.example.moviecrupapp.Components.DaggerDComponent

class MyApplication : Application() {
        lateinit var appComponent: DComponent

        override fun onCreate() {
            super.onCreate()

            appComponent = DaggerDComponent.builder()
                .application(this)
                .build()

        }
    }
