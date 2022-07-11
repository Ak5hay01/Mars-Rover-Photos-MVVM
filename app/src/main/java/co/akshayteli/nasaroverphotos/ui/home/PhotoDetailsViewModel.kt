package co.akshayteli.nasaroverphotos.ui.home

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.akshayteli.nasaroverphotos.data.model.Photo
import co.akshayteli.nasaroverphotos.ui.repository.PhotoDetailsRepository
import co.akshayteli.nasaroverphotos.data.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(private val photosRepository: PhotoDetailsRepository) : ViewModel() {

    private val photos = MutableLiveData<Response<ArrayList<Photo>>>()
    private var currentPage = 1
    var listState: Parcelable? = null

    fun getPhotos() = photos

    fun getCurrentPage() = currentPage

    fun loadData() {
        try {
            if (currentPage == 1) {
                photos.postValue(Response.InProgress)
            }
            viewModelScope.launch(Dispatchers.IO) {
                val response = photosRepository.getPhotosFromApi(1000, currentPage)
                response?.let {
                    val photosList = it.body()?.photos
                    photosList?.let { list ->
                        if (currentPage == 1) { //load data for First page
                            photos.postValue(Response.Success(list))
                        } else { //add photos to current list
                            val currentPhotos: ArrayList<Photo>? = photos.value?.extractData
                            if (currentPhotos == null || currentPhotos.isEmpty()) {
                                photos.postValue(Response.Success(list))
                            } else {
                                currentPhotos.addAll(list)
                                photos.postValue(Response.Success(currentPhotos))
                            }
                        }
                    } ?: run {
                        photos.postValue(Response.Success(arrayListOf())) // send data to Home Fragment using postValue
                    }
                } ?: run {
                    photos.postValue(Response.Success(arrayListOf()))
                }
            }
        } catch (error: Exception) {
            photos.postValue(Response.Error(error))
        }
    }

    fun loadDataNextPage() {
        currentPage++
        loadData()
    }
}