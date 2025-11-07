package ru.itis.effectivemobiletesttask.nav

import ru.itis.effectivemobiletesttask.core_navigation.Nav
import ru.itis.effectivemobiletesttask.core_navigation.NavLoginScreen
import ru.itis.effectivemobiletesttask.feature_auth.impl.LoginFragmentDirections
import javax.inject.Inject

class NavLoginScreenImpl @Inject constructor(
    private val navigatorDelegate: NavigatorDelegate,
) : NavLoginScreen {

    private var parent: Nav? = null

    override fun initNavIncome(parent: Nav) {
        this.parent = parent
    }

    override fun goToMainScreen() {
        val action = LoginFragmentDirections
            .actionLoginFragmentToMainFragment()
        navigatorDelegate.navigate(action)

    }
}
