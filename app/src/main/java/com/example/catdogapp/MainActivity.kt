package com.example.catdogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.catdogapp.dataSource.DogService
import com.example.catdogapp.databinding.ActivityMainBinding
import com.example.catdogapp.repositories.DogRepository
import com.example.catdogapp.viewModels.DogVMfactory
import com.example.catdogapp.viewModels.DogViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DogViewModel
    private val dogService = DogService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, DogVMfactory(DogRepository(dogService))).get(
                DogViewModel::class.java
            )

        viewModel.getDogRandom()
    }

    override fun onStart() {
        super.onStart()

        binding.dogImgView.setOnClickListener{
            viewModel.getDogRandom()
        }
        binding.button.setOnClickListener {
            val breed = binding.edtText.text.toString()
            viewModel.getDogByBreed(breed)
        }

        viewModel.randomDog.observe(this, Observer {
            loadImg(it)
        })
        viewModel.breedDog.observe(this, Observer {
            loadImg(it)
        })
    }

    private fun loadImg(url: String){
        Glide.with(this@MainActivity).load(url).into(binding.dogImgView)
    }
}