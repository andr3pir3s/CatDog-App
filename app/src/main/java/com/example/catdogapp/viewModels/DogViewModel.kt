package com.example.catdogapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catdogapp.models.Dog
import com.example.catdogapp.repositories.DogRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_OK

class DogViewModel(private val repository: DogRepository): ViewModel() {

    val randomDog = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

    val breedDog = MutableLiveData<String>()

    fun getDogRandom() {
        val request = this.repository.getDogRandom()
        request.enqueue(object : Callback<Dog>{
            override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                if (response.code() == HTTP_OK){
                    randomDog.postValue(response.body()?.message)
                }
                else{
                    errorMessage.postValue("${response.code()}")
                }
            }
            override fun onFailure(call: Call<Dog>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

    fun getDogByBreed(breed: String) {
        val request = this.repository.getDogByBreed(breed)
        request.enqueue(object : Callback<Dog>{
            override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                if (response.code() == HTTP_OK){
                    breedDog.postValue(response.body()?.message)
                }else{
                    errorMessage.postValue("${response.code()}")
                }
            }

            override fun onFailure(call: Call<Dog>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}