package com.example.route.e_commerce.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.route.e_commerce.R
import com.example.route.e_commerce.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveNavController()
        initBottomNavView()
    }

    private fun retrieveNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun initBottomNavView() {
        binding.content.bottomNav.itemIconTintList = null
        binding.content.bottomNav.setupWithNavController(navController)
        binding.content.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    binding.content.header.searchBar.visibility= View.VISIBLE
                    binding.content.header.cart.visibility = View.VISIBLE
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.categoriesFragment -> {
                    navController.navigate(R.id.categoriesFragment)
                    true
                }

                R.id.profileFragment -> {
                    binding.content.header.searchBar.visibility= View.GONE
                    binding.content.header.cart.visibility = View.GONE
                    navController.navigate(R.id.profileFragment)
                    true
                }

                R.id.wishListFragment -> {
                    navController.navigate(R.id.wishListFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}