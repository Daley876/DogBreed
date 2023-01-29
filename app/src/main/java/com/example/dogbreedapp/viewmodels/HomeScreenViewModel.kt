package com.example.dogbreedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedapp.models.DogsBreedModel
import com.example.dogbreedapp.repositories.DogApiRepo
import kotlinx.coroutines.*

class HomeScreenViewModel : ViewModel() {
    private val repository = DogApiRepo()

     val dogBreedStateData : MutableState<DogsBreedModel> = mutableStateOf(DogsBreedModel("",listOf("")))

     fun getDogsByBreed (breed : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getDogsByBreed(breed)
            if (response != null) {
                dogBreedStateData.value = response
            }
        }
    }

}