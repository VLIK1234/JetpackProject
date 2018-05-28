package com.example.bakachie.jetpackproject.ui.main

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.bakachie.jetpackproject.App
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.config.Config
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        navController = host.navController
        setupActionBar(navController)

        setupBottomNavMenu(navController)

        navController.addOnNavigatedListener { _, destination ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            Toast.makeText(this@MainActivity, "$this@Navigated to $dest",
                    Toast.LENGTH_SHORT).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }

        handleDeeplink()
    }

    override fun onResume() {
        super.onResume()
        Config.isFirstStart = false
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeeplink()
    }

    private fun setupActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawerLayout,
                Navigation.findNavController(this, R.id.my_nav_host_fragment))
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav_view?.let { bottomNavView ->
            NavigationUI.setupWithNavController(bottomNavView, navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Have the NavHelper look for an action or destination matching the menu
        // item id and navigate there if found.
        // Otherwise, bubble up to the parent.

        return NavigationUI.onNavDestinationSelected(item,
                Navigation.findNavController(this, R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    private fun handleDeeplink() {
        if (TextUtils.isEmpty(App.prefs?.token)) return

        if (intent.data != null) {
            navController.onHandleDeepLink(intent)
        } else {
            intent.extras?.let {
                val activityArgs = MainActivityArgs.fromBundle(it)

                if (!TextUtils.isEmpty(activityArgs.deeplinkUri)) {
                    intent.data = Uri.parse(activityArgs.deeplinkUri)
                    navController.onHandleDeepLink(intent)
                }}
        }
    }
}
