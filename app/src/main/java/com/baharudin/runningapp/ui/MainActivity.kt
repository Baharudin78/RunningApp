package com.baharudin.runningapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.baharudin.runningapp.R
import com.baharudin.runningapp.databinding.ActivityMainBinding
import com.baharudin.runningapp.other.Constant.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)

        val navigationController = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navigationController.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.settingFragment, R.id.statisticFragment, R.id.runFragment ->
                    binding.bottomNavigationView.visibility = View.VISIBLE
                else -> binding.bottomNavigationView.visibility = View.GONE
            }
        }
        navController = navigationController.findNavController()

        binding.apply {
            bottomNavigationView.setupWithNavController(navController)
        }

    }

    //memanggil clas dibawahnii
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }
    //navigasi ke tracking fragment jika user meng klik notification
    private fun navigateToTrackingFragmentIfNeeded(intent : Intent?){
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            binding.navHostFragment.findNavController().navigate(R.id.action_from_notification_to_trackingfragment)
        }
    }
}