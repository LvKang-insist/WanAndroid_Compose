package com.lvkang.wadandroid.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * @name ThemeType
 * @package com.lvkang.wadandroid.ui.theme
 * @author 345 QQ:1831712732
 * @time 2022/05/16 21:19
 * @description
 */

//主题状态
val themeTypeState: MutableState<ThemeType> by lazy {
    mutableStateOf(ThemeType.SKY_BLUE_THEME)
}

fun getThemeForTheme(type: ThemeType): Colors =
    when (type) {
        ThemeType.LIGHT_THEME -> LightColorScheme
        ThemeType.DARK_THEME -> DarkColorScheme
        ThemeType.SKY_BLUE_THEME -> BlueColorScheme
        ThemeType.GRAY_THEME -> GrayColorScheme
        ThemeType.DEEP_BLUE_THEME -> DeepBlueColorScheme
        ThemeType.GREEN_THEME -> GreenScheme
        ThemeType.PURPLE_THEME -> PurpleScheme
        ThemeType.ORANGE_THEME -> OrangeColorScheme
        ThemeType.BROWN_THEME -> BrownColorScheme
        ThemeType.RED_THEME -> RedColorScheme
        ThemeType.CYAN_THEME -> CyanColorScheme
        ThemeType.MAGENTA_THEME -> MagentaColorScheme
    }


enum class ThemeType {

    //暗色
    DARK_THEME,

    //亮色
    LIGHT_THEME,

    // 天蓝色
    SKY_BLUE_THEME,

    // 灰色
    GRAY_THEME,

    // 深蓝色
    DEEP_BLUE_THEME,

    // 绿色
    GREEN_THEME,

    // 紫色
    PURPLE_THEME,

    // 橘黄色
    ORANGE_THEME,

    // 棕色
    BROWN_THEME,

    // 红色
    RED_THEME,

    // 青色
    CYAN_THEME,

    // 品红色
    MAGENTA_THEME,
}