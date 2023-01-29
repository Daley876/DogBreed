package com.example.dogbreedapp.models


import com.example.dogbreedapp.interfaces.DogApiCalls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogBreedApi {
    const val BASE_URL = "https://dog.ceo/api/breed/"

    val instance by lazy {
        val retrofit =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        retrofit.create(DogApiCalls::class.java)
    }

}