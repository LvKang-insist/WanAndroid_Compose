package com.lvkang.wadandroid.core.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @name Router
 * @package com.lvkang.wadandroid.core.app
 * @author 345 QQ:1831712732
 * @time 2022/05/18 22:41
 * @description
 */

class NavigationItem(val name: String, val icon: ImageVector)

val navigationItems =
    listOf(
        NavigationItem("首页", Icons.Default.Home),
        NavigationItem("项目", Icons.Default.Info),
        NavigationItem("分类", Icons.Default.Favorite),
        NavigationItem("我的", Icons.Default.Person),
    )

enum class Routers(router: String) {
    //登录页面
    MAIN("main"),
    LOGIN("login"),
    HOME_DETAIL("home/detail"),
}