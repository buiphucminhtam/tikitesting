package com.tiki.tikitesting.data.models

data class HomeBannerEntity(
    val imageUrl: String = ""
)

data class QuickLinkEntity(
    val title: String = "",
    val imageUrl: String = ""
)

data class FlashDealEntity(
    val imageUrl: String = "",
    val price: String,
    val qtyOrdered: Int = 0,
    val qty: Int = 0,
    val discountPercent: Int = 0
)