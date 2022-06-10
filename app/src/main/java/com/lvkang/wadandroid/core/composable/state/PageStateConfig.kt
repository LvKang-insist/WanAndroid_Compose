package com.lvkang.wadandroid.core.composable.state

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable

/**
 * @name PageStateConfig
 * @package com.lvkang.wadandroid.core.composable.state
 * @author 345 QQ:1831712732
 * @time 2022/06/08 22:10
 * @description
 */
object PageStateConfig {

    var loadingComponent: @Composable (BoxScope.() -> Unit)? = null
    var errorComponent: @Composable (BoxScope.(PageStateData.Error) -> Unit)? = null
    var emptyComponent: @Composable (BoxScope.(PageStateData.Empty) -> Unit)? = null


    fun loadingComponent(component: @Composable (BoxScope.() -> Unit)) {
        this.loadingComponent = component
    }

    fun emptyComponent(component: @Composable (BoxScope.(PageStateData.Empty) -> Unit)) {
        this.emptyComponent = component
    }

    fun errorComponent(component: @Composable (BoxScope.(PageStateData.Error) -> Unit)) {
        this.errorComponent = component
    }
}