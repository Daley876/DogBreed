package com.example.dogbreedapp.network


import com.example.dogbreedapp.network.interfaces.DogBreedApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConnection {
    private const val BASE_URL = "https://dog.ceo/api/breed/"

    private val retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val dogBreedApiService: DogBreedApiService by lazy {
        retrofit.create(DogBreedApiService::class.java)
    }



    val apiClient = ApiClient(dogBreedApiService)
}