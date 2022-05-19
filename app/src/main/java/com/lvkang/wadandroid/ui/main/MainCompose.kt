package com.lvkang.wadandroid.ui.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lvkang.wadandroid.composable.SetScaffold
import com.lvkang.wadandroid.core.app.navigationItems
import com.lvkang.wadandroid.ui.home.*

/**
 * @name MainCompose
 * @package com.lvkang.wadandroid.ui.Main
 * @author 345 QQ:1831712732
 * @time 2022/05/18 23:32
 * @description
 */

@Composable
fun MainCompose(navController: NavHostController, mainBottomState: MutableState<Int>) {
    SetScaffold(
        bottomBar = {
            BottomBar(mainBottomState)
        }
    ) {
        when (mainBottomState.value) {
            0 -> HomeCompos(navController)
            1 -> ProjectFragment()
            2 -> FLFragment()
            else -> UserFragment()
        }
    }
}


@Composable
private fun BottomBar(mainBottomState: MutableState<Int>) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
    ) {

        navigationItems.forEachIndexed { index, navigationItem ->
            BottomNavigationItem(
                selected = mainBottomState.value == index,
                onClick = {
                    mainBottomState.value = index
                },
                icon = {
                    Icon(
                        imageVector = navigationItem.icon,
                        contentDescription = navigationItem.name
                    )
                },
                label = {
                    BottomText(
                        isSelect = mainBottomState.value == index,
                        name = navigationItem.name
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black
            )
        }
    }
}

@Composable
fun BottomText(isSelect: Boolean, name: String) {
    if (isSelect) {
        Text(
            text = name,
            color = MaterialTheme.colors.primary,
            fontSize = 12.sp
        )
    } else {
        Text(
            text = name,
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}


