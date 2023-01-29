package com.example.dogbreedapp.repositories

import com.example.dogbreedapp.network.NetworkConnection
import com.example.dogbreedapp.models.DogsBreedModel

class DogApiRepo {
    private val client = NetworkConnection

     suspend fun getDogsByBreed(breedName: String): DogsBreedModel?{
         val request = client.apiClient.getDogsByBreed(breedName)

         return if (request.isSuccessful) request.body()!! else null
    }
}