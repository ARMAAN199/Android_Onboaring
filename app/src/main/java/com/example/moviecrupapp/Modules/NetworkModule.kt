package com.example.moviecrupapp.Modules

import com.example.moviecrupapp.API.API
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module
class NetworkModule {

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/";
    }

    @Provides
    fun getApiInstance(retrofit: Retrofit) : API {
        return retrofit.create(API::class.java)
    }

}