package com.akshayteli.nasamarsrover_mvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akshayteli.nasamarsrover_mvvm.data.api.ApiService
import com.akshayteli.nasamarsrover_mvvm.data.repository.PhotosRepository

/**
 * Created by Akshay Teli on 10,July,2022
 */
class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(
                PhotosRepository(apiService)
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}