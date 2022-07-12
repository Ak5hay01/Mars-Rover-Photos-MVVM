package co.akshayteli.nasaroverphotos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.akshayteli.nasaroverphotos.R
import co.akshayteli.nasaroverphotos.databinding.ActivityMainBinding
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

        val bottomNavigation = binding.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.home_dest))

        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
