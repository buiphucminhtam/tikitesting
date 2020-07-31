package com.tiki.tikitesting.data.models

data class Response<T>(
    val data: List<T>?
)

data class HomeBannerResponse(
    val id: String?,
    val title: String?,
    val content: String?,
    val url: String?,
    val imageUrl: String?,
    val thumbnail_url: String?,
    val mobile_url: String?,
    val ratio: String?
)

data class QuickLinkResponse(
    val authentication: Boolean?,
    val content: String?,
    val image_url: String?,
    val title: String?,
    val url: String?,
    val web_url: String?
)

data class FlashDealResponse(
    val discount_percent: Int?,
    val from_date: String?,
    val product: ProductResponse?,
    val progress: ProgressResponse?,
    val special_from_date: Int?,
    val special_price: Int?,
    val special_to_date: Int?,
    val status: Int?,
    val tags: String?,
    val url: String?
)

data class ProductResponse(
    val badges: List<BadgeResponse?>?,
    val discount: Int?,
    val id: Int?,
    val inventory: InventoryResponse?,
    val is_flower: Boolean?,
    val is_fresh: Boolean?,
    val is_gift_card: Boolean?,
    val is_visible: Boolean?,
    val list_price: Int?,
    val master_id: Int?,
    val name: String?,
    val order_count: Int?,
    val price: Int?,
    val price_prefix: String?,
    val rating_average: Int?,
    val review_count: Int?,
    val seller_product_id: Int?,
    val sku: String?,
    val thumbnail_url: String?,
    val url_attendant_input_form: String?,
    val url_path: String?
)

data class InventoryResponse(
    val fulfillment_type: String?,
    val product_virtual_type: Any?
)

data class BadgeResponse(
    val code: String
)

data class ProgressResponse(
    val qty: Int?,
    val qty_ordered: Int?,
    val qty_remain: Int?,
    val percent: Double?,
    val status: String?
)