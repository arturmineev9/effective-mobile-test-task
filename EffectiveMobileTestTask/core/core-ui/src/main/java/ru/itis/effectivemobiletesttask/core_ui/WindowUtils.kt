package ru.itis.effectivemobiletesttask.core_ui

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat

object WindowUtils {

    fun setSystemBarsColor(activity: Activity, @ColorRes statusBarColor: Int, @ColorRes navigationBarColor: Int) {
        val window = activity.window

        window.statusBarColor = ContextCompat.getColor(activity, statusBarColor)
        window.navigationBarColor = ContextCompat.getColor(activity, navigationBarColor)

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = false
        controller.isAppearanceLightNavigationBars = false
    }
}