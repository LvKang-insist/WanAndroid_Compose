@file:OptIn(ExperimentalAnimationApi::class)

package com.lvkang.wadandroid.ui


import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.*
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lvkang.wadandroid.core.app.Routers
import com.lvkang.wadandroid.core.base.BaseComponentActivity
import com.lvkang.wadandroid.ui.home.*
import com.lvkang.wadandroid.ui.main.MainCompose


class MainActivity : BaseComponentActivity() {


    private val mainBottomState by lazy {
        mutableStateOf(0)
    }

    @Composable
    override fun SetContent() {
        val navController = rememberAnimatedNavController()

        AnimatedNavHost(
            navController = navController,
            startDestination = Routers.MAIN.name,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(300)
                )
            }
        ) {
            composable(Routers.MAIN.name) {
                MainCompose(navController = navController, mainBottomState)
            }
            composable(Routers.HOME_DETAIL.name) {

            }
        }

    }

    private fun NavGraphBuilder.animatedComposable(
        routers: Routers,
        content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
    ) {
        composable(
            route = routers.name
        ) { backStackEntry ->
            content(backStackEntry)
        }
    }
}


