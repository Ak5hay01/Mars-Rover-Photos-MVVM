package co.akshayteli.nasaroverphotos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import co.akshayteli.nasaroverphotos.R
import co.akshayteli.nasaroverphotos.databinding.ActivityMainBinding
import co.akshayteli.nasaroverphotos.utils.extensions.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        toolBar = binding.toolbar
        setSupportActionBar(toolBar)
        if (savedInstanceState == null) {
            setBottomNavigationBar()
        }
        setContentView(binding.root)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNav
        val navGraphIds = listOf(R.navigation.nav_graph_home)

        val controller = bottomNavigationView.setupWithNavController(navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent)

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
