package com.example.dogbreedapp.interfaces

import com.example.dogbreedapp.models.DogsBreed
import retrofit2.Call
import retrofit2.http.GET

interface DogApiCalls {

   @GET("breed/hound/images")//this represents end points of url for the specific call
   fun retrieveDogsByBreed(): Call<DogsBreed>

}