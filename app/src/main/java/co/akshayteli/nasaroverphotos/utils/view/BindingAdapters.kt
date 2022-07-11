package co.akshayteli.nasaroverphotos.utils.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import co.akshayteli.nasaroverphotos.utils.extensions.setImageFromUrl

class BindingAdapters {
    companion object {
        @BindingAdapter("android:image_url")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, auctionImageUrl: String) {
            imageView.setImageFromUrl(auctionImageUrl)
        }
    }
}