package co.akshayteli.nasaroverphotos.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.akshayteli.nasaroverphotos.R
import co.akshayteli.nasaroverphotos.data.model.Photo
import co.akshayteli.nasaroverphotos.databinding.RowPhotoHomeBinding
import co.akshayteli.nasaroverphotos.ui.details.PhotoDetailsFragment
import co.akshayteli.nasaroverphotos.utils.extensions.setImageFromUrlWithProgressBar


class PhotoDetailsAdapter(private val photosList: ArrayList<Photo>) :
    RecyclerView.Adapter<PhotoDetailsAdapter.PhotoDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowPhotoHomeBinding.inflate(inflater, parent, false)
        return PhotoDetailsViewHolder(binding)
    }

    override fun getItemCount() = photosList.size

    override fun onBindViewHolder(holder: PhotoDetailsViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    class PhotoDetailsViewHolder(rowBinding: RowPhotoHomeBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding
        //Logic for binding the data
        fun bind(photo: Photo) {
            binding.photoBinding = photo
            binding.executePendingBindings()
            binding.rowPhotoRoverImg.setImageFromUrlWithProgressBar(photo.imgSrc,
                binding.rowPhotoRoverProgress)
            binding.root.setOnClickListener { view ->
                val bundle = bundleOf("PHO_ARG" to photo)
                view.findNavController().navigate(R.id.action_home_to_details, bundle)

            }
        }
    }
}