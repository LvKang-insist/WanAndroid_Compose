package com.lvkang.wadandroid.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lvkang.wadandroid.composable.SetScaffold
import com.lvkang.wadandroid.core.app.Routers

/**
 * @name HomeFragment
 * @package com.lvkang.wadandroid.ui.home
 * @author 345 QQ:1831712732
 * @time 2022/05/17 23:48
 * @description
 */

@Composable
fun HomeCompos(navController: NavHostController) {


    SetScaffold(title = "首页") {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            Button(onClick = {
                navController.navigate(Routers.HOME_DETAIL.name) {
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(text = "12345", fontSize = 50.sp)
            }
            Text(text = "12345", fontSize = 50.sp)
            Text(text = "12345", fontSize = 50.sp)
            Log.e("---345--->", "-----------首页");
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeDetail() {
    LaunchedEffect(Unit) {
        Log.e("---345--->", "LaunchedEffect");
    }

    val state  = rememberScaffoldState();


    Scaffold(scaffoldState = state) {

    }

    DisposableEffect(Unit) {
        onDispose {
            Log.e("---345--->", "DisposableEffect")
        }
    }

    SideEffect {
        Log.e("---345--->", "SideEffect");
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Text(text = "12345", fontSize = 50.sp)
        Text(text = "12345", fontSize = 50.sp)
    }
}

@Composable
fun ProjectFragment() {
    Text(text = "项目")
}


@Composable
fun FLFragment() {
    Text(text = "分类")
}


@Composable
fun UserFragment() {
    Text(text = "我的")
}