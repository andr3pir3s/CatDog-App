package com.example.catdogapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catdogapp.repositories.DogRepository

class DogVMfactory(private val repository: DogRepository): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DogViewModel::class.java)){
            DogViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not Found!")
        }
    }
}