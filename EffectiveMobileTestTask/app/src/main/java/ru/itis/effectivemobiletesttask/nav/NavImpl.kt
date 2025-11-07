package ru.itis.effectivemobiletesttask.nav

import ru.itis.effectivemobiletesttask.core_navigation.Nav
import ru.itis.effectivemobiletesttask.core_navigation.NavLoginScreen
import javax.inject.Inject

class NavImpl @Inject constructor(
    private val navigatorDelegate: NavigatorDelegate,
    private val navMain: NavLoginScreen,
) : Nav, NavLoginScreen by navMain {

    init {
        initNavIncome(parent = this)
    }

    override fun setNavProvider(navProvider: Nav.Provider) {
        navigatorDelegate.setNavProvider(navProvider = navProvider)
    }

    override fun clearNavProvider(navProvider: Nav.Provider) {
        navigatorDelegate.clearNavProvider(navProvider = navProvider)
    }
}
