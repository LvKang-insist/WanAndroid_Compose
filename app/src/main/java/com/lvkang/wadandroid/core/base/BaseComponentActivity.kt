package com.lvkang.wadandroid.core.base


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lvkang.wadandroid.ui.theme.PrimaryTheme

/**
 * @name BaseComponentActivity
 * @package com.lvkang.wadandroid.core.base
 * @author 345 QQ:1831712732
 * @time 2022/05/17 21:28
 * @description
 */

abstract class BaseComponentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SetImmersion()
            PrimaryTheme {
                SetContent()
            }
        }
    }


    @Composable
    private fun SetImmersion() {
        if (isImmersion()) {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.run {
                    setSystemBarsColor(color = Color.Transparent, darkIcons = isDark())
                    setNavigationBarColor(color = Color.Black)
                }
            }
        }
    }




    @Composable
    open fun SetContent() = Unit

    open fun isImmersion(): Boolean = true
    open fun isDark(): Boolean = false

}