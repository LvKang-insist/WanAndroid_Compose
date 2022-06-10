package com.lvkang.wadandroid.core.composable.state

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

/**
 * @name PageStateCompose
 * @package com.lvkang.wadandroid.core.composable.state
 * @author 345 QQ:1831712732
 * @time 2022/06/08 22:19
 * @description
 */

sealed class PageStateData {
    data class Success<T>(val t: T?) : PageStateData()
    data class Error(val throwable: Throwable? = null, val value: Any? = null) : PageStateData()
    data class Empty(val any: Any? = null) : PageStateData()
    object Loading : PageStateData()
}

class PageState(stateData: PageStateData) {

    private var interactionState by mutableStateOf(stateData)

    val state get() = interactionState

    fun changeState(stateData: PageStateData) {
        interactionState = stateData
    }

    val isLoading: Boolean
        get() = interactionState is PageStateData.Loading

    companion object {
        fun loading(throwable: Throwable? = null, value: Any? = null) = PageStateData.Loading
        fun error() = PageStateData.Error()
        fun empty(any: Any? = null) = PageStateData.Empty()
        fun <T> success(t: T? = null) = PageStateData.Success(t)
    }

}


@Composable
fun rememberPageState(state: PageStateData = PageStateData.Loading): PageState {
    return remember() {
        PageState(state)
    }
}

@Composable
fun <T> StateCompose(
    modifier: Modifier = Modifier,
    pageState: PageState = rememberPageState(),
    errorComponent: @Composable (BoxScope.(PageStateData.Error) -> Unit)? = PageStateConfig.errorComponent,
    emptyComponent: @Composable (BoxScope.(PageStateData.Empty) -> Unit)? = PageStateConfig.emptyComponent,
    loadingComponent: @Composable (BoxScope.() -> Unit)? = PageStateConfig.loadingComponent,
    loading: (() -> Unit)? = null,
    contentComponent: @Composable (BoxScope.(PageStateData.Success<T>) -> Unit)
) {
    Box(modifier = modifier) {
        when (pageState.state) {
            is PageStateData.Success<*> -> {
                contentComponent.invoke(this, pageState.state as PageStateData.Success<T>)
            }
            is PageStateData.Loading -> {
                loadingComponent?.invoke(this)
                loading?.invoke()
            }
            is PageStateData.Error -> {
                errorComponent?.invoke(this, pageState.state as PageStateData.Error)
            }
            is PageStateData.Empty -> {
                emptyComponent?.invoke(this, pageState.state as PageStateData.Empty)
            }
        }
    }

}