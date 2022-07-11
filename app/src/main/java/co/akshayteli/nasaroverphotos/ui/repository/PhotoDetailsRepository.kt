package co.akshayteli.nasaroverphotos.ui.repository

import android.util.Log
import co.akshayteli.nasaroverphotos.BuildConfig
import co.akshayteli.nasaroverphotos.data.api.ApiCall
import co.akshayteli.nasaroverphotos.data.model.PhotosResponse
import retrofit2.Response

class PhotoDetailsRepository(val apiService: ApiCall) {

    var apiKey: String = BuildConfig.API_KEY

//    Fetch data from API using Coroutines
    suspend fun getPhotosFromApi(sol: Int, page: Int): Response<PhotosResponse>? {
        try {
            val response = apiService.getPhotosFromAPI(sol, apiKey, page)
            response?.let {
                return it
            }
        } catch (error: Exception) {
            return null
        }
        return null
    }

}