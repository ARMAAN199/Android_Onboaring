package com.example.moviecrupapp.Components

import com.example.moviecrupapp.MainActivity
import com.example.moviecrupapp.Modules.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class])
interface DComponent {
    fun inject(mainActivity: MainActivity)
}