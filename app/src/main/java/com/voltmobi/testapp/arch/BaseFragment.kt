package com.voltmobi.testapp.arch

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voltmobi.testapp.R

abstract class BaseFragment : Fragment() {
    protected var navController: NavController? = null
    protected var tabBar: BottomNavigationView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = activity?.let { Navigation.findNavController(it, R.id.my_nav_host_fragment) }
        tabBar = activity?.findViewById(R.id.bottomNavigationView)
    }

    fun showMessege(messege: String) {
        Toast.makeText(context?.applicationContext, messege, Toast.LENGTH_LONG).show()
    }
}