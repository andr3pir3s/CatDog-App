package com.example.catdogapp.repositories

import com.example.catdogapp.dataSource.DogService

class DogRepository (private val dogService: DogService) {

    fun getDogByBreed(breed: String) = dogService.getDogByBreed(breed)
    fun getDogRandom() = dogService.getDogRandom()
}