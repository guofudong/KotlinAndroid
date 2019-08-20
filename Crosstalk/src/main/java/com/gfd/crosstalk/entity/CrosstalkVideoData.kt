package com.gfd.crosstalk.entity

/**
 * @Author ：郭富东
 * @Date：2019/8/19:14:50
 * @Email：878749089@qq.com
 * @description：相声视频数据实体类
 */
data class CrosstalkVideoData(
        val `data`: List<Data>?,
        val action_label: String,
        val action_label_pgc: String,
        val action_label_web: String,
        val city: String,
        val count: Int,
        val cur_tab: Int,
        val has_more: Int,
        val message: String,
        val offset: Int,
        val page_id: String,
        val query_id: String,
        val request_id: String,
        val return_count: Int,
        val show_tabs: Int,
        val tab: Tab,
        val tokens: List<String>
)

data class Tab(
        val cur_tab: Int,
        val tab_list: List<TabX>
)

data class TabX(
        val tab_code: String,
        val tab_id: Int,
        val tab_name: String
)

data class Data(
        val `abstract`: String,
        val article_url: String,
        val behot_time: String,
        val comment_count: Int,
        val comments_count: Int,
        val create_time: String,
        val datetime: String,
        val display_time: String,
        val group_id: String,
        val group_source: Int,
        val has_gallery: Boolean,
        val has_image: Boolean,
        val has_video: Boolean,
        val highlight: Highlight,
        val id: String,
        val image_count: Int,
        val image_list: List<Image>,
        val image_url: String,
        val item_id: String,
        val item_source_url: String,
        val keyword: String,
        val labels: List<Any>,
        val large_image_url: String,
        val large_mode: Boolean,
        val media_avatar_url: String,
        val media_creator_id: Long,
        val media_name: String,
        val media_url: String,
        val middle_image_url: String,
        val middle_mode: Boolean,
        val more_mode: Boolean,
        val open_url: String,
        val play_effective_count: String,
        val publish_time: String,
        val seo_url: String,
        val share_url: String,
        val show_play_effective_count: Int,
        val single_mode: Boolean,
        val source: String,
        val source_url: String,
        val summary: String,
        val tag: String,
        val tag_id: Long,
        val title: String,
        val user_auth_info: UserAuthInfo,
        val user_id: Long,
        val video_duration: Int,
        val video_duration_str: String?
)

data class Highlight(
        val `abstract`: List<List<Int>>,
        val source: List<List<Int>>,
        val summary: List<List<Int>>,
        val title: List<Any>
)

data class Image(
        val url: String
)

data class UserAuthInfo(
        val auth_info: String,
        val auth_type: String
)