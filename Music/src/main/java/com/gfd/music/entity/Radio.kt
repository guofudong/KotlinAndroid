package com.gfd.music.entity

data class Radio(
        val `data`: RData?,
        val code: Int,
        val result: String
)

data class RData(
        val djRadios: List<DjRadio>,
        val djRadiosCount: Int
)

data class DjRadio(
        val buyed: Boolean,
        val category: String,
        val categoryId: Int,
        val commentCount: Int,
        val composeVideo: Boolean,
        val createTime: Long,
        val desc: String,
        val discountPrice: Any,
        val dj: Dj,
        val feeScope: Int,
        val finished: Boolean,
        val id: Int,
        val lastProgramCreateTime: Long,
        val lastProgramId: Int,
        val lastProgramName: String,
        val likedCount: Int,
        val name: String,
        val originalPrice: Int,
        val picId: Long,
        val picUrl: String,
        val price: Int,
        val programCount: Int,
        val purchaseCount: Int,
        val radioFeeType: Int,
        val rcmdText: Any,
        val rcmdtext: Any,
        val shareCount: Int,
        val subCount: Int,
        val underShelf: Boolean,
        val videos: Any
)

data class Dj(
        val accountStatus: Int,
        val authStatus: Int,
        val authority: Int,
        val avatarImgId: Long,
        val avatarImgIdStr: String,
        val avatarImgId_str: String,
        val avatarUrl: String,
        val backgroundImgId: Long,
        val backgroundImgIdStr: String,
        val backgroundUrl: String,
        val birthday: Long,
        val city: Int,
        val defaultAvatar: Boolean,
        val description: String,
        val detailDescription: String,
        val djStatus: Int,
        val expertTags: Any,
        val experts: Any,
        val followed: Boolean,
        val gender: Int,
        val mutual: Boolean,
        val nickname: String,
        val province: Int,
        val remarkName: Any,
        val signature: String,
        val userId: Int,
        val userType: Int,
        val vipType: Int
)