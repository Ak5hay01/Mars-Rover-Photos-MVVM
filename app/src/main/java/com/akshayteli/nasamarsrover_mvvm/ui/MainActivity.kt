package com.akshayteli.nasamarsrover_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.akshayteli.nasamarsrover_mvvm.R
import com.akshayteli.nasamarsrover_mvvm.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}
