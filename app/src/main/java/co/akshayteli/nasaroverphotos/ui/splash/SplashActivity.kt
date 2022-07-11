package co.akshayteli.nasaroverphotos.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.akshayteli.nasaroverphotos.ui.MainActivity
import co.akshayteli.nasaroverphotos.databinding.ActivitySplashBinding
import java.util.*


class SplashActivity : AppCompatActivity() {

    private val TIMER = 3_000L
    private lateinit var binding: ActivitySplashBinding
    private var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivitySplashBinding.inflate(layoutInflater)
            setContentView(binding.root)
            navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        val task = object : TimerTask() {
            override fun run() {
                goToMainActivity()
            }
        }
        timer.schedule(task, TIMER)
    }

    private fun goToMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
