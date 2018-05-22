package com.example.bakachie.jetpackproject

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.bakachie.jetpackproject.ui.main.MainViewModel
import kotlinx.android.synthetic.main.detail_fragment.*

class MainActivity : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return


        // Set up Action Bar
        val navController = host.navController
//        setupActionBar(navController)
//
//        setupNavigationMenu(navController)
//
//        setupBottomNavMenu(navController)

        navController.addOnNavigatedListener { _, destination ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            Toast.makeText(this@MainActivity, "Navigated to $dest",
                    Toast.LENGTH_SHORT).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }
}
