package com.example.dogbreedapp.interfaces

import com.example.dogbreedapp.models.Dogs
import retrofit2.Call

interface DogApiCall {
   fun retrieveDogs(): Call<List<Dogs>>
}