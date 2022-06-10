package com.lvkang.wadandroid.bean

/**
 * @name BannListBean
 * @package com.lvkang.wadandroid.bean
 * @author 345 QQ:1831712732
 * @time 2022/06/09 18:33
 * @description
 */
class BannerListBean : ArrayList<BannerListBean.BannerListBeanItem>(){
    data class BannerListBeanItem(
        val desc: String, // 一起来做个App吧
        val id: Int, // 30
        val imagePath: String, // https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png
        val isVisible: Int, // 1
        val order: Int, // 0
        val title: String, // 一起来做个App吧
        val type: Int, // 0
        val url: String // https://www.wanandroid.com/blog/show/2
    )
}