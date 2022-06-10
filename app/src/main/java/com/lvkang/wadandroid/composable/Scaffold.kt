package com.lvkang.wadandroid.composable

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @name Scaffold
 * @package com.lvkang.wadandroid.composable
 * @author 345 QQ:1831712732
 * @time 2022/05/18 16:46
 * @description
 */

@Composable
inline fun ComponentActivity.SetScaffold(
    title: String? = null,
    noinline topBar: @Composable (() -> Unit)? = null,
    noinline bottomBar: @Composable () -> Unit,
    noinline actions: @Composable RowScope.() -> Unit = {},
    crossinline content: @Composable (PaddingValues) -> Unit,
    noinline back: (() -> Unit)? = null
) {
    Scaffold(
        topBar = {
            topBar?.run { this.invoke() } ?: title?.run {
                TopAppBarCenter(
                    modifier = Modifier.statusBarsPadding(),
                    title = { Text(text = title) },
                    actions = actions,
                    back = { finish() }
                )
            }
        },
        bottomBar = bottomBar,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            content.invoke(it)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
inline fun SetScaffold(
    title: String? = null,
    noinline topBar: @Composable (() -> Unit)? = null,
    noinline bottomBar: @Composable () -> Unit = {},
    noinline actions: @Composable RowScope.() -> Unit = {},
    noinline back: (() -> Unit)? = null,
    background: Color? = null,
    crossinline content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            topBar?.run { this.invoke() } ?: title?.run {
                TopAppBarCenter(
                    modifier = Modifier.statusBarsPadding(),
                    title = { Text(text = title) },
                    actions = actions,
                    back = { back?.invoke() }
                )
            }
        },
        backgroundColor = background ?: MaterialTheme.colors.onBackground,
        bottomBar = bottomBar,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            content.invoke(it)
        }
    }
}