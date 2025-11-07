package ru.itis.effectivemobiletesttask.nav

import androidx.navigation.NavDirections
import ru.itis.effectivemobiletesttask.core_navigation.Nav
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorDelegate @Inject constructor() {
    private var navProvider: Nav.Provider? = null
    fun setNavProvider(navProvider: Nav.Provider) {
        this.navProvider = navProvider
    }

    fun clearNavProvider(navProvider: Nav.Provider) {
        if (this.navProvider == navProvider) {
            this.navProvider = null
        }
    }

    fun navigate(directions: NavDirections) {
        navProvider?.getNavController()?.navigate(directions)
    }
}
