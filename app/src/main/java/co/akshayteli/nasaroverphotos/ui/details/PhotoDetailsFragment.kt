package co.akshayteli.nasaroverphotos.ui.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.akshayteli.nasaroverphotos.ui.MainActivity
import co.akshayteli.nasaroverphotos.data.model.Photo
import co.akshayteli.nasaroverphotos.databinding.FragmentPhotoDetailsBinding
import co.akshayteli.nasaroverphotos.databinding.FragmentPhotoDetailsBindingImpl
import co.akshayteli.nasaroverphotos.utils.extensions.setImageFromUrl
import co.akshayteli.nasaroverphotos.utils.extensions.setImageFromUrlWithProgressBar

class PhotoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPhotoDetailsBinding
    private lateinit var photo: Photo
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadSentDataArguments()
    }

    // Get information for the selected Item
    private fun loadSentDataArguments() {
        arguments?.getParcelable<Photo>("photo")?.let {
            photo = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentPhotoDetailsBindingImpl.inflate(inflater)
        binding.photoBinding = photo
        binding.lifecycleOwner = this@PhotoDetailsFragment
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        binding.photoDetailsImageZoom.setImageFromUrl(photo.imgSrc)
        binding.photoDetailsImage.setImageFromUrlWithProgressBar(photo.imgSrc, binding.photoDetailsProgress)
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.show()
        super.onResume()
    }
}
