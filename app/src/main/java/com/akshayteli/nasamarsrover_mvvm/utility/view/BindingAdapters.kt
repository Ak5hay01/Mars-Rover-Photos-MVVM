package com.akshayteli.nasamarsrover_mvvm.utility.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.akshayteli.nasamarsrover_mvvm.utility.extensions.setImageFromUrl

class BindingAdapters {
    companion object {
        @BindingAdapter("android:image_url")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, auctionImageUrl: String) {
            imageView.setImageFromUrl(auctionImageUrl)
        }
    }
}