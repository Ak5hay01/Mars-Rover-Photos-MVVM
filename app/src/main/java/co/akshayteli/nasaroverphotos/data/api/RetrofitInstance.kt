package co.akshayteli.nasaroverphotos.data.api

import co.akshayteli.nasaroverphotos.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var client: OkHttpClient = OkHttpClient.Builder().build()

//    Retrofit instance for getting the API details using the retrofit lib
    private var retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                                             .addConverterFactory(GsonConverterFactory.create())
                                             .client(client).build()

    fun <T> createService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}