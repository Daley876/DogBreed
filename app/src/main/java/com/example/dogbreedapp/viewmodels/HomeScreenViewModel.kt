package com.example.dogbreedapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedapp.models.DogsBreed
import com.example.dogbreedapp.repositories.DogApiRepo
import kotlinx.coroutines.*

class HomeScreenViewModel : ViewModel() {
    private val repository = DogApiRepo()

    val dogBreedLiveData : MutableLiveData<DogsBreed> = MutableLiveData()


     fun getDogs (breed : String){
         viewModelScope.launch (Dispatchers.IO){
             val res : DogsBreed = repository.getDogsApiCall(breed)
             dogBreedLiveData.postValue(res)
         }
    }
}