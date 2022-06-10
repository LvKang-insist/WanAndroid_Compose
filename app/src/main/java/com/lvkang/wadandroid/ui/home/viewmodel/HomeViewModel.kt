package com.lvkang.wadandroid.ui.home.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.lvhttp.net.launch.launchHttp
import com.lvkang.wadandroid.bean.ArticleListBean
import com.lvkang.wadandroid.bean.BannerListBean
import com.lvkang.wadandroid.core.base.BaseViewModel
import com.lvkang.wadandroid.core.composable.state.PageState
import com.lvkang.wadandroid.core.net.api.HomeApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @name HomeViewModel
 * @package com.lvkang.wadandroid.ui.home.viewmodel
 * @author 345 QQ:1831712732
 * @time 2022/06/08 22:43
 * @description
 */
class HomeViewModel : BaseViewModel() {

    private val _banner by lazy { MutableLiveData<BannerListBean>() }
    val banner: LiveData<BannerListBean> = _banner

    val projects = Pager(PagingConfig(pageSize = 20)) {
        HomeListSource(homeApi)
    }.flow.cachedIn(viewModelScope)


    fun banner() {
        viewModelScope.launch {
            launchHttp {
                homeApi.banner()
            }.toData {
                _banner.value = it.data
                pageState.changeState(PageState.success(""))
            }
        }
    }
}

class HomeListSource(private val homeApi: HomeApi) : PagingSource<Int, ArticleListBean.Data>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleListBean.Data>): Int? = null


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleListBean.Data> {
        val nextPage = params.key ?: 0
        return try {
            val data = homeApi.homeList(nextPage).data.datas
            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (nextPage < 100) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}