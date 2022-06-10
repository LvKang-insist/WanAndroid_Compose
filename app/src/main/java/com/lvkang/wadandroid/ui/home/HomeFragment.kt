package com.lvkang.wadandroid.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lvkang.wadandroid.bean.ArticleListBean
import com.lvkang.wadandroid.bean.BannerListBean
import com.lvkang.wadandroid.composable.Banner
import com.lvkang.wadandroid.composable.SetScaffold
import com.lvkang.wadandroid.ui.home.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

/**
 * @name HomeFragment
 * @package com.lvkang.wadandroid.ui.home
 * @author 345 QQ:1831712732
 * @time 2022/05/17 23:48
 * @description
 */


@Composable
fun HomeCompose(
    navController: NavHostController,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    SetScaffold(title = "首页") {
        val refreshState = rememberSwipeRefreshState(isRefreshing = true)
        val bannerState = viewModel.banner.observeAsState()
        val pagingItems = viewModel.projects.collectAsLazyPagingItems()
        if (pagingItems.itemCount > 0) {
            refreshState.isRefreshing = false
        }
        if (bannerState.value == null) viewModel.banner()
        SwipeRefresh(state = refreshState, onRefresh = {
            refreshState.isRefreshing = true
            viewModel.banner()
            pagingItems.refresh()
        }) {
            HomeList(list = pagingItems, bannerState.value)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeList(list: LazyPagingItems<ArticleListBean.Data>, bannerListData: BannerListBean?) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            //轮播图
            Banner(bannerListData)
        }
        itemsIndexed(list) { _, data ->
            if (data == null) return@itemsIndexed
            Card(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.padding(horizontal = 12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (data.fresh) {
                            Text(
                                text = "新",
                                style = MaterialTheme.typography.subtitle2.merge(TextStyle(Color.White)),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colors.primary)
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(text = "作者:", style = MaterialTheme.typography.subtitle2)
                        Text(
                            text = data.author.ifBlank { "未知" },
                            style = MaterialTheme.typography.subtitle2
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "分类:", style = MaterialTheme.typography.subtitle2)
                        Text(
                            text = data.superChapterName.ifBlank { "官方" },
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = data.title,
                        style = MaterialTheme.typography.h5,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = data.niceDate, style = MaterialTheme.typography.subtitle2)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
        when (list.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Text(text = "加载中....")
                }
            }
            is LoadState.Error -> {
                item {
                    Text(text = "加载失败")
                }
            }
        }
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