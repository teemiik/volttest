package com.voltmobi.testapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.voltmobi.testapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_show_inflation -> {
                    if (navController.currentDestination?.id != R.id.showInflationFragment) {
                        navController.navigate(R.id.action_calculateInflationFragment_to_showInflationFragment)
                    }
                }
                R.id.action_show_calculator -> {
                    if (navController.currentDestination?.id != R.id.calculateInflationFragment) {
                        navController.navigate(R.id.action_showInflationFragment_to_calculateInflationFragment)
                    }
                }
            }
            false
        }

    }

    override fun onBackPressed() {}
}

