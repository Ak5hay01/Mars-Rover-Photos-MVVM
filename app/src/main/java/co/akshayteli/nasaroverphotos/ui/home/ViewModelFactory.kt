package co.akshayteli.nasaroverphotos.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.akshayteli.nasaroverphotos.data.api.ApiCall
import co.akshayteli.nasaroverphotos.ui.repository.PhotoDetailsRepository

class ViewModelFactory(private val apiService: ApiCall) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoDetailsViewModel::class.java)) {
            return PhotoDetailsViewModel(
                PhotoDetailsRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}