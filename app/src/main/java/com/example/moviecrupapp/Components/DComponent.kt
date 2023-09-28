package com.example.moviecrupapp.Components

import com.example.moviecrupapp.MainActivity
import com.example.moviecrupapp.Modules.DataBaseModule
import com.example.moviecrupapp.Modules.NetworkModule
import com.example.moviecrupapp.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataBaseModule::class])
interface DComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): DComponent
    }
}