package com.gfd.crosstalk.entity

/**
 * @Author ：郭富东
 * @Date：2019/8/19:14:50
 * @Email：878749089@qq.com
 * @description：相声视频数据实体类
 */
data class CrosstalkVideoData(
    val `data`: List<Data>?
)

data class Data(
    val `abstract`: String,
    val cell_type: Int,
    val comment_count: Int,
    val comments_count: Int,
    val composition: Int,
    val create_time: String?,
    val datetime: String?,
    val display: Display,
    val display_mode: String,
    val display_time: String,
    val display_type_ext: String,
    val image_count: Int,
    val image_list: List<Image>,
    val image_url: String,
    val item_source_url: String,
    val labels: List<Any>,
    val large_image_url: String?,
    val media_avatar_url: String,
    val media_creator_id: Long,
    val media_name: String,
    val media_url: String,
    val middle_image_url: String?,
    val open_url: String,
    val play_effective_count: String,
    val publish_time: String?,
    val source_url: String?,
    val title: String?,
    val video_duration: Int,
    val video_duration_str: String?
)

data class Image(
    val url: String
)

data class Preload(
    val css: List<String>,
    val html: List<String>,
    val js: List<String>
)

data class DataExt(
    val is_title_full_matched: Boolean
)

data class Emphasized(
    val source: String,
    val summary: String,
    val title: String
)

data class Display(
    val data_ext: DataExt,
    val emphasized: Emphasized,
    val info: Info,
    val summary: Summary,
    val title: Title
)

data class Title(
    val marked: String,
    val pos: List<List<Int>>,
    val text: String
)

data class Info(
    val _comment: String,
    val app_download: Boolean,
    val author: String,
    val domain: String,
    val enable_ua: Boolean,
    val host: String,
    val icon_img: String,
    val icon_name: String,
    val images: List<String>,
    val play_effective_count: String,
    val preload: Preload,
    val site_name: String,
    val site_refer: String,
    val time_factor: Int,
    val time_factor_string: String,
    val type: String,
    val type_ext: String,
    val url: String,
    val video_duration: Int,
    val video_duration_str: String
)

data class Summary(
    val marked: String,
    val pos: List<Any>,
    val text: String
)