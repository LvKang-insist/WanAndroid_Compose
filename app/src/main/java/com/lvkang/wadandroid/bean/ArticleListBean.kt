package com.lvkang.wadandroid.bean

/**
 * @name ArticleList
 * @package com.lvkang.wadandroid.bean
 * @author 345 QQ:1831712732
 * @time 2022/06/09 16:26
 * @description
 */data class ArticleListBean(
    val curPage: Int, // 2
    val datas: List<Data>,
    val offset: Int, // 20
    val over: Boolean, // false
    val pageCount: Int, // 622
    val size: Int, // 20
    val total: Int // 12424
) {
    data class Data(
        val apkLink: String,
        val audit: Int, // 1
        var author: String,
        val canEdit: Boolean, // false
        val chapterId: Int, // 502
        val chapterName: String, // 自助
        val collect: Boolean, // false
        val courseId: Int, // 13
        val desc: String,
        val descMd: String,
        val envelopePic: String,
        val fresh: Boolean, // false
        val host: String,
        val id: Int, // 22967
        val link: String, // https://juejin.cn/post/7105645114760331300
        val niceDate: String, // 2天前
        val niceShareDate: String, // 2天前
        val origin: String,
        val prefix: String,
        val projectLink: String,
        val publishTime: Long, // 1654529623000
        val realSuperChapterId: Int, // 493
        val selfVisible: Int, // 0
        val shareDate: Long, // 1654529234000
        val shareUser: String, // 鸿洋
        val superChapterId: Int, // 494
        val superChapterName: String, // 广场Tab
        val tags: List<Tag>,
        val title: String, // Android 13 返回导航大变更：返回键彻底废弃 + 可预见型返回手势
        val type: Int, // 0
        val userId: Int, // 2
        val visible: Int, // 1
        val zan: Int // 0
    ) {
        data class Tag(
            val name: String, // 公众号
            val url: String // /wxarticle/list/408/1
        )
    }
}