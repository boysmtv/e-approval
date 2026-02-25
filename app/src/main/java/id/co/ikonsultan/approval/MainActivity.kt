package id.co.ikonsultan.approval

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupWithNavController
import id.co.ikonsultan.approval.navigation.AppNavigatorImpl
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHost.navController

        bottomNav = findViewById(R.id.bottomNavigation)

        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val showBottomBar = destination.id in setOf(
                R.id.homeFragment,
                R.id.historyFragment
            )
            bottomNav.visibility = if (showBottomBar) View.VISIBLE else View.GONE
        }

        bottomNav.itemIconSize = resources.getDimensionPixelSize(id.co.ikonsultan.approval.core.ui.R.dimen.icon_16)
        bottomNav.itemRippleColor = null

        handleBackPressed()
    }

    private fun handleBackPressed() {
        onBackPressedDispatcher.addCallback(this) {

            when (navController.currentDestination?.id) {

                R.id.homeFragment -> {
                    if (System.currentTimeMillis() - backPressedTime < 2000) {
                        finish()
                    } else {
                        backPressedTime = System.currentTimeMillis()
                        Toast.makeText(
                            this@MainActivity,
                            "Press back again to exit",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                R.id.historyFragment -> {
                    navController.popBackStack()
                }

                else -> {
                    navController.popBackStack()
                }
            }
        }
    }
}