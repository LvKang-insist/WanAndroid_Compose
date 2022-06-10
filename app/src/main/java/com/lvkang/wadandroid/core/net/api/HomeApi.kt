package com.lvkang.wadandroid.core.net.api

import com.lvkang.wadandroid.bean.ArticleListBean
import com.lvkang.wadandroid.bean.BannerListBean
import com.lvkang.wadandroid.core.net.resposne.ResponseData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @name HomeApi
 * @package com.lvkang.wadandroid.core.net.api
 * @author 345 QQ:1831712732
 * @time 2022/06/09 15:59
 * @description
 */
interface HomeApi {
    @GET("article/list/{page}/json")
    suspend fun homeList(@Path("page") page: Int): ResponseData<ArticleListBean>

    @GET("banner/json")
    suspend fun banner(): ResponseData<BannerListBean>
}