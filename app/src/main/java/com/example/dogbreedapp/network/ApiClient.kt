package com.example.dogbreedapp.network

import com.example.dogbreedapp.network.interfaces.DogBreedApiService
import com.example.dogbreedapp.models.DogsBreedModel
import retrofit2.Response

class ApiClient(private val dogBreedApiService: DogBreedApiService) {

    suspend fun getDogsByBreed(breed: String) : Response<DogsBreedModel>{
        return dogBreedApiService.retrieveDogsByBreed(breed)
    }

}
