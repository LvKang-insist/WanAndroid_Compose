package com.lvkang.wadandroid.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat


@Composable
fun PrimaryTheme(
    themeType: ThemeType = themeTypeState.value,
    content: @Composable () -> Unit
) {
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(12.dp),
    )
    MaterialTheme(
        colors = getThemeForTheme(themeType),
        typography = Typography,
        shapes = shapes,
        content = content
    )
}