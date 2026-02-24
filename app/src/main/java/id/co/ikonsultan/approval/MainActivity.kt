package id.co.ikonsultan.approval

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar = findViewById(R.id.bottomBar)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        navController = navHost.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomBar.visibility = when (destination.id) {
                R.id.homeFragment,
                R.id.historyFragment -> View.VISIBLE
                else -> View.GONE
            }
        }
    }
}