package com.akshayteli.nasamarsrover_mvvm.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.akshayteli.nasamarsrover_mvvm.data.model.Photo
import com.akshayteli.nasamarsrover_mvvm.databinding.RoverSingleRowBinding
import com.akshayteli.nasamarsrover_mvvm.ui.detail.DetailsFragment
import com.akshayteli.nasamarsrover_mvvm.utility.extensions.setImageFromUrlWithProgressBar
import androidx.navigation.findNavController
import com.akshayteli.nasamarsrover_mvvm.R


/**
 * Created by Akshay Teli on 09,July,2022
 */
class PhotosAdapter(private val photosList: ArrayList<Photo>) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoverSingleRowBinding.inflate(inflater, parent, false)
        return PhotosViewHolder(
            binding)
    }

    override fun getItemCount() = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    class PhotosViewHolder(rowBinding: RoverSingleRowBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding

        fun bind(photo: Photo) {
            binding.photoBinding = photo
            binding.executePendingBindings()
            binding.rowPhotoRoverImg.setImageFromUrlWithProgressBar(photo.imgSrc,
                binding.rowPhotoRoverProgress)
            binding.root.setOnClickListener { view ->
                val bundle = bundleOf(DetailsFragment.PHOTO_ARG to photo)
             view.findNavController().navigate(R.id.action_home_to_details,bundle)




            }
        }
    }
}


