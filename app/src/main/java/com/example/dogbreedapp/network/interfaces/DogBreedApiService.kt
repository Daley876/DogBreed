package com.example.dogbreedapp.network.interfaces

import com.example.dogbreedapp.models.DogsBreedModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedApiService {

   @GET("{breed}/images")//this represents end points of url for the specific call
   suspend fun retrieveDogsByBreed(
      @Path("breed") breed: String
   ): Response<DogsBreedModel>

}