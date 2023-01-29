package com.example.dogbreedapp.repositories

import android.util.Log
import com.example.dogbreedapp.models.DogBreedApi
import com.example.dogbreedapp.models.DogsBreed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogApiRepo {
    private val client = DogBreedApi

     fun getDogsByBreed(breed: String): DogsBreed {
        var data = DogsBreed("", listOf(""))
        val response = client.instance.retrieveDogsByBreed(breed)

        response.enqueue(object : Callback<DogsBreed> {
            override fun onResponse(call: Call<DogsBreed?>, response: Response<DogsBreed>) {
                data = response.body()!!
            }

            override fun onFailure(call: Call<DogsBreed>, t: Throwable) {
                Log.d("ERROR_LOG", t.toString())
            }
        })

        return data
    }

    suspend fun getDogsApiCall(breed: String) : DogsBreed{
        var output = DogsBreed("", listOf(""))
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val response =  async {
                    getDogsByBreed(breed)
                }.await()
                if(response.status.equals("success")) {
                    println("=====API CALL WAS GOOD=====")
                output = response
                }
                else {println("=====API CALL WAS BAD======")}
            }
        } catch (e: Exception){
            println(e.toString())
        }
        return output
    }
}