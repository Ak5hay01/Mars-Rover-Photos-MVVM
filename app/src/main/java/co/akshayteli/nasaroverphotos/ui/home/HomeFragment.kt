package co.akshayteli.nasaroverphotos.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.akshayteli.nasaroverphotos.ui.MainActivity
import co.akshayteli.nasaroverphotos.R
import co.akshayteli.nasaroverphotos.data.api.ApiCall
import co.akshayteli.nasaroverphotos.data.api.RetrofitInstance
import co.akshayteli.nasaroverphotos.data.model.Photo
import co.akshayteli.nasaroverphotos.databinding.FragmentHomeBinding
import co.akshayteli.nasaroverphotos.databinding.FragmentHomeBindingImpl
import co.akshayteli.nasaroverphotos.data.model.Response
import co.akshayteli.nasaroverphotos.utils.list.InfiniteScrollListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    companion object {
        private val TAG = HomeFragment::class.java.name
    }

    private lateinit var binding: FragmentHomeBinding
    private var photoDetailsViewModel: PhotoDetailsViewModel? = null

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBindingImpl.inflate(inflater)
        binding.lifecycleOwner = this@HomeFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindings() // initialization of bindings
        initViewModels() //initialization of ViewModel
        getDataFromAPI()
    }

    private fun initBindings() {
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.homePhotosList.run {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    private fun initViewModels() {
        if (null == photoDetailsViewModel) {
            photoDetailsViewModel = ViewModelProvider(this@HomeFragment,
                ViewModelFactory(RetrofitInstance.createService(ApiCall::class.java))).get(
                PhotoDetailsViewModel::class.java)
            photoDetailsViewModel?.loadData()
        }
    }

    private fun getDataFromAPI() {
        photoDetailsViewModel?.getPhotos()?.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Response.Success -> {
                    renderList(result.data)
                    binding.homePhotosProgressContainer.visibility = View.GONE
                    binding.homePhotosList.visibility = View.VISIBLE
                }
                is Response.InProgress -> {
                    binding.homePhotosProgressContainer.visibility = View.VISIBLE
                    binding.homePhotosList.visibility = View.GONE
                }
                is Response.Error -> {
                    binding.homePhotosProgressContainer.visibility = View.GONE
                    Toast.makeText(activity, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun fetchMoreData() {
        photoDetailsViewModel?.loadDataNextPage()
    }

    private fun renderList(photos: ArrayList<Photo>) {
        if (photos.isNotEmpty()) {
            if (photoDetailsViewModel?.getCurrentPage() == 1 || binding.homePhotosList.adapter?.itemCount == 0) {
                setRecyclerData(photos)
            } else {
                if (binding.homePhotosList.adapter == null) {
                    setRecyclerData(photos)
                    binding.homePhotosList.adapter?.notifyDataSetChanged()
                }
            }
            if (photoDetailsViewModel?.listState != null) {
                binding.homePhotosList.layoutManager?.onRestoreInstanceState(photoDetailsViewModel?.listState)
                photoDetailsViewModel?.listState = null
            }
        } else {
            showSnackBarMessage()
        }
    }

    private fun setRecyclerData(photos: ArrayList<Photo>) {
        with(binding.homePhotosList) {
            adapter = PhotoDetailsAdapter(photos)
            addOnScrollListener(InfiniteScrollListener({ fetchMoreData() },
                layoutManager as LinearLayoutManager))
        }
    }

    private fun showSnackBarMessage() {
        val bottomNavView: BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
        Snackbar.make(bottomNavView, R.string.no_data_msg, Snackbar.LENGTH_SHORT).apply {
            anchorView = bottomNavView
        }.show()
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.show()
        super.onResume()
    }

    override fun onDestroyView() {
        photoDetailsViewModel?.listState = binding.homePhotosList.layoutManager?.onSaveInstanceState()
        super.onDestroyView()
    }
}
