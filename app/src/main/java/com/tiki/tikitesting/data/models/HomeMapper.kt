package com.tiki.tikitesting.data.models

import android.util.Log
import java.text.DecimalFormat
import java.text.NumberFormat

fun List<HomeBannerResponse>.toListHomeBannerEntity():List<HomeBannerEntity>{
    val listMapped = arrayListOf<HomeBannerEntity>()
    if (this.isNotEmpty()) {
        forEach { item -> listMapped.add(item.toHomeBannerEntity()) }
    }
    return listMapped
}

fun HomeBannerResponse.toHomeBannerEntity():HomeBannerEntity{
    return HomeBannerEntity(mobile_url?:"")
}

fun List<QuickLinkResponse>.toListQuickLinkEntity():List<QuickLinkEntity>{
    val listMapped = arrayListOf<QuickLinkEntity>()
    if (this.isNotEmpty()) {
        forEach { item -> listMapped.add(item.toQuickLinkEntity()) }
    }
    return listMapped
}

fun QuickLinkResponse.toQuickLinkEntity():QuickLinkEntity{
    return QuickLinkEntity(title?:"",image_url?:"")
}

fun FlashDealResponse.toFlashDealEntity(): FlashDealEntity {
    var image = ""
    var price = ""
    var qty = 0
    var qty_ordered = 0
    product?.let {
        image = it.thumbnail_url?:""
        if (it.price != null) {
            price = NumberFormat.getInstance().format(it.price).replace(",",".") + " đ"
        }
    }

    progress?.let {
        qty = it.qty?:0
        qty_ordered = it.qty_ordered?:0
    }
    return FlashDealEntity(image,price,qty_ordered,qty,discount_percent?:0)
}

fun List<FlashDealResponse>.toListFlashDealEntity():List<FlashDealEntity>{
    val listMapped = arrayListOf<FlashDealEntity>()
    if (this.isNotEmpty()) {
        forEach { item -> listMapped.add(item.toFlashDealEntity()) }
    }
    return listMapped
}