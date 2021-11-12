package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private val appBarConfiguration by lazy {
    AppBarConfiguration(
        topLevelDestinationIds =
            setOf(R.id.loginFragment, R.id.welcomeFragment, R.id.shoeListFragment))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    Timber.plant(Timber.DebugTree())

    setSupportActionBar(binding.toolbar)
    val navController = findNavController()
    NavigationUI.setupActionBarWithNavController(this, navController)
    NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
  }

  private fun findNavController(): NavController {
    val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    return navHostFragment.navController
  }

  override fun onSupportNavigateUp(): Boolean {
    return findNavController().navigateUp() || super.onSupportNavigateUp()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.logout, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val handled = NavigationUI.onNavDestinationSelected(item, findNavController())
    if (!handled) {
      when(item.itemId) {
        R.id.action_logout -> {
          doLogout()
        }
      }
    }
    return true
  }

  private fun doLogout() {
    findNavController().graph = findNavController().graph
  }
}
