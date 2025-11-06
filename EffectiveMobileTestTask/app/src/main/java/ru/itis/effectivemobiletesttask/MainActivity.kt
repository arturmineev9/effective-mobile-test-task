package ru.itis.effectivemobiletesttask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.effectivemobiletesttask.core_navigation.Nav
import ru.itis.effectivemobiletesttask.databinding.ActivityMainBinding
import ru.itis.effectivemobiletesttask.nav.NavImpl
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Nav.Provider {

    @Inject
    lateinit var nav: Nav

    private var viewBinding: ActivityMainBinding? = null
    private var navController: NavController? = null
    private var mainContainer = R.id.main_container_id


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
        setupNavigation()
        setupBottomNavigation()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = ContextCompat.getColor(this, ru.itis.effectivemobiletesttask.core_ui.R.color.color_dark)
        window.statusBarColor = getColor(ru.itis.effectivemobiletesttask.core_ui.R.color.color_dark)
    }

    private fun setupNavigation() {
        if (navController == null) {
            val navHost =
                supportFragmentManager.findFragmentById(mainContainer) as NavHostFragment
            navController = navHost.navController
        }
        nav.setNavProvider(navProvider = this)
    }

    private fun setupBottomNavigation() {
        if (navController != null) {
            navController!!.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.mainFragment,
                    R.id.favoritesFragment,
                    R.id.accountFragment -> {
                        viewBinding?.bottomNav?.visibility = View.VISIBLE
                    }

                    else -> {
                        viewBinding?.bottomNav?.visibility = View.GONE
                    }
                }
            }
            viewBinding?.bottomNav?.setupWithNavController(navController!!)
        }
    }

    override fun getNavController(): NavController? {
        return navController
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::nav.isInitialized) {
            nav.clearNavProvider(navProvider = this)
        }
        viewBinding = null
    }

}
