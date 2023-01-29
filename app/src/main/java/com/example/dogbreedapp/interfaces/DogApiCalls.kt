package com.example.dogbreedapp.interfaces

import com.example.dogbreedapp.models.DogsBreed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiCalls {

   @GET("{breed}/images")//this represents end points of url for the specific call
   fun retrieveDogsByBreed(@Path("breed") breed: String): Call<DogsBreed>

}