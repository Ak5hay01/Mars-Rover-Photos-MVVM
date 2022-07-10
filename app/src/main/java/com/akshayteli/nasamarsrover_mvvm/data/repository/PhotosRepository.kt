package com.akshayteli.nasamarsrover_mvvm.data.repository

import android.util.Log
import com.akshayteli.nasamarsrover_mvvm.BuildConfig
import com.akshayteli.nasamarsrover_mvvm.data.api.ApiService
import com.akshayteli.nasamarsrover_mvvm.data.model.PhotosResponse
import retrofit2.Response

/**
 * Created by Akshay Teli on 10,July,2022
 */
class PhotosRepository (val apiService: ApiService) {

    private val TAG = PhotosRepository::class.java.name

    var apiKey: String = BuildConfig.API_KEY

    suspend fun getPhotosFromApi(sol: Int, page: Int): Response<PhotosResponse>? {
        try {
            val response = apiService.getPhotos(sol, apiKey, page)
            response?.let {
                return it
            }
        } catch (error: Exception) {
            Log.e(TAG, "Error: ${error.message}")
            return null
        }
        return null
    }

}