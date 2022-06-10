package com.lvkang.wadandroid.core.base

import androidx.lifecycle.ViewModel
import com.lvhttp.net.LvHttp
import com.lvkang.wadandroid.core.composable.state.PageState
import com.lvkang.wadandroid.core.net.api.HomeApi

/**
 * @name BaseViewModel
 * @package com.lvkang.wadandroid.core.base
 * @author 345 QQ:1831712732
 * @time 2022/06/09 16:17
 * @description
 */
open class BaseViewModel : ViewModel() {

    val homeApi by lazy { LvHttp.createApi(HomeApi::class.java) }

    val pageState by lazy { PageState(PageState.loading()) }

}