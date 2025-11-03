package ru.itis.effectivemobiletesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.effectivemobiletesttask.core_navigation.Nav
import ru.itis.effectivemobiletesttask.nav.NavImpl
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Nav.Provider {

    @Inject
    lateinit var navImpl: NavImpl

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navImpl.setNavProvider(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navImpl.clearNavProvider(this)
    }

    override fun getNavController(): NavController = navController
}
