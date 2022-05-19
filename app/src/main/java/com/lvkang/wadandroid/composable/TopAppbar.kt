package com.lvkang.wadandroid.composable

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @name TopAppbar
 * @package com.lvkang.wadandroid.composable
 * @author 345 QQ:1831712732
 * @time 2022/05/17 22:39
 * @description
 */

/**
 * @param title 标题
 * @param modifier Modifier，设置后会和已经设置好Modifier进行合并
 * @param navigationIcon 左边图标
 * @param backgroundColor 背景颜色
 * @param actions 右边图标
 * @param isImmersive 是否是沉浸式
 * @param darkIcons 状态栏的文字图标颜色是黑色还是白色，默认是false（白色）
 */
@Composable
fun TopAppBarCenter(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colors.primary,
    actions: @Composable RowScope.() -> Unit = {},
    isImmersive: Boolean = true,
    darkIcons: Boolean = false,
    back: (() -> Unit)? = null
) {
    val topAppBarHeight = 56.dp
    var statusBarHeight = 0
    var statusBarHeightDp = Dp(0f)
    if (isImmersive) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = darkIcons
            )
        }
        with(LocalContext.current) {
            statusBarHeight =
                resources.getDimensionPixelSize(
                    resources.getIdentifier(
                        "status_bar_height",
                        "dimen",
                        "android"
                    )
                )
        }
        with(LocalDensity.current) {
            statusBarHeightDp = statusBarHeight.toDp()
        }
    }
    val constraintSet = ConstraintSet {
        val titleRef = createRefFor("title")
        val navigationIconRef = createRefFor("navigationIcon")
        val actionsRef = createRefFor("actions")
        constrain(titleRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(navigationIconRef) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(actionsRef) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        }
    }
    ConstraintLayout(
        constraintSet, modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .then(modifier)
            .height(topAppBarHeight)
//            .height(topAppBarHeight + statusBarHeightDp)
//            .padding(top = statusBarHeightDp)
    ) {
        Box(
            Modifier
                .layoutId("title")
                .padding(horizontal = 4.dp)
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.body2.merge(TextStyle(color = MaterialTheme.colors.onPrimary))) {
                CompositionLocalProvider(
                    content = title
                )
            }
        }

        Box(
            modifier = Modifier
                .layoutId("navigationIcon")
                .padding(start = 4.dp)
        ) {
            if (navigationIcon != null) {
                CompositionLocalProvider(
                    content = navigationIcon
                )
            } else {
                CompositionLocalProvider(
                    content = {
                        IconButton(onClick = { back?.invoke() }) {
                            Image(
                                painter = painterResource(id = com.lvkang.wadandroid.R.drawable.one),
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(32.dp),
                                contentScale = ContentScale.Crop,
                                contentDescription = "返回"
                            )
                        }
                    }
                )
            }


        }
        Row(
            Modifier
                .layoutId("actions")
                .padding(end = 4.dp),
            content = actions
        )

    }


}