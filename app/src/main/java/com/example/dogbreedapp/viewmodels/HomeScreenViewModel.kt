package com.example.dogbreedapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedapp.models.DogsBreedModel
import com.example.dogbreedapp.repositories.DogApiRepo
import kotlinx.coroutines.*

class HomeScreenViewModel : ViewModel() {
    private val repository = DogApiRepo()

    private val dogBreedMutableLiveDataFromCall = MutableLiveData<DogsBreedModel?>()
    val dogBreedLiveData : LiveData<DogsBreedModel?> = dogBreedMutableLiveDataFromCall

     fun getDogsByBreed (breed : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getDogsByBreed(breed)
            dogBreedMutableLiveDataFromCall.postValue(response)
        }
    }

}