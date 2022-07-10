package com.akshayteli.nasamarsrover_mvvm.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akshayteli.nasamarsrover_mvvm.data.model.Photo
import com.akshayteli.nasamarsrover_mvvm.databinding.FragmentDetailsBinding
import com.akshayteli.nasamarsrover_mvvm.databinding.FragmentDetailsBindingImpl
import com.akshayteli.nasamarsrover_mvvm.ui.MainActivity
import com.akshayteli.nasamarsrover_mvvm.utility.extensions.setImageFromUrl
import com.akshayteli.nasamarsrover_mvvm.utility.extensions.setImageFromUrlWithProgressBar
import com.akshayteli.nasamarsrover_mvvm.utility.extensions.zoomImageFromThumb


class DetailsFragment : Fragment() {

    companion object {
        private val TAG = DetailsFragment::class.java.name
        const val PHOTO_ARG = "PHOTO_ARG"
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var photo: Photo
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadArguments()
    }

    private fun loadArguments() {
        arguments?.getParcelable<Photo>(PHOTO_ARG)?.let {
            photo = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBindingImpl.inflate(inflater)
        binding.photoBinding = photo
        binding.lifecycleOwner = this@DetailsFragment
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        binding.photoDetailsImageZoom.setImageFromUrl(photo.imgSrc)
        with(binding.photoDetailsImage) {
            setImageFromUrlWithProgressBar(photo.imgSrc, binding.photoDetailsProgress)
            setOnClickListener {
                zoomImageFromThumb(binding.photoDetailsImageZoom,
                    binding.photoDetailsContainer,
                    shortAnimationDuration)
            }
        }
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.show()
        super.onResume()
    }
}
