package com.akshayteli.nasamarsrover_mvvm.data.model


import com.google.gson.annotations.SerializedName

data class PhotosResponse(@SerializedName("photos") val photos: ArrayList<Photo>)