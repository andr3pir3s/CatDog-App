package com.example.catdogapp.dataSource

import com.example.catdogapp.models.Dog
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://dog.ceo/"

interface DogService {

    @GET("api/breed/{breed}/images/random")
    fun getDogByBreed(
        @Path("breed") breed: String
    ): Call<Dog>

    @GET("api/breeds/image/random")
    fun getDogRandom(): Call<Dog>

    companion object {

        private val retrofitService: DogService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(DogService::class.java)

        }

        fun getInstance() = retrofitService
    }
}