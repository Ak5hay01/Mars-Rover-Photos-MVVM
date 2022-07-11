package co.akshayteli.nasaroverphotos.data.api

import co.akshayteli.nasaroverphotos.data.model.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

//    API call definition with the query string parameters
    @GET("photos")
    suspend fun getPhotosFromAPI
                (@Query("sol") sol: Int,
                 @Query("api_key") apiKey: String,
                 @Query("page") page: Int): Response<PhotosResponse>?
}