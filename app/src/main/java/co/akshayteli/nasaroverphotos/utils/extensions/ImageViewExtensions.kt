package co.akshayteli.nasaroverphotos.utils.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.net.toUri
import co.akshayteli.nasaroverphotos.utils.view.GlideApp
import co.akshayteli.nasaroverphotos.utils.view.PhotosRequestListener

fun ImageView.setImageFromUrl(imageUrl: String) {
    GlideApp.with(context).load(imageUrl).thumbnail(0.1f).into(this)
}

fun ImageView.setImageFromUrlWithProgressBar(url: String, progress: View) {
    GlideApp.with(this.context).load(url.toUri().buildUpon().scheme("https").build()).thumbnail(0.1f).listener(PhotosRequestListener(progress))
        .into(this)
}


