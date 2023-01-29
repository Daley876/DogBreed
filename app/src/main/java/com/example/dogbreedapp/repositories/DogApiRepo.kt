package com.example.dogbreedapp.repositories

import com.example.dogbreedapp.network.NetworkConnection
import com.example.dogbreedapp.models.DogsBreedModel

class DogApiRepo {
    private val client = NetworkConnection

     /*fun getDogsByBreed(breed: String): DogsBreedResponse {
        var data = DogsBreedResponse("", listOf(""))
        val response = client.apiClient.getDogsByBreed(breed)

        response.enqueue(object : Callback<DogsBreedResponse> {
            override fun onResponse(call: Call<DogsBreedResponse?>, response: Response<DogsBreedResponse>) {
                data = response.body()!!
            }

            override fun onFailure(call: Call<DogsBreedResponse>, t: Throwable) {
                Log.d("ERROR_LOG", t.toString())
                println("========ENQUEUE WAS BAD=========")
            }
        })

        return data
    }*/

     suspend fun getDogsByBreed(breedName: String): DogsBreedModel?{
         val request = client.apiClient.getDogsByBreed(breedName)

         return if (request.isSuccessful) request.body()!! else null
    }
}