package com.evernext10.mercadolibre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.evernext10.mercadolibre.databinding.ActivityMainBinding
import com.evernext10.navigation.Destination
import com.evernext10.navigation.setFragmentNavigationListener

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        supportFragmentManager.setFragmentNavigationListener(this) { destination ->
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            when (destination) {
                is Destination.PockemonList -> {
                    navController.navigate(R.id.action_to_productList)
                }
                is Destination.PockemonDetail -> {
                    navController.navigate(R.id.action_to_productDetail, bundleOf("pockemon" to destination.pockemon))
                }
                else -> {
                    navController.navigate(R.id.action_to_productList)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
