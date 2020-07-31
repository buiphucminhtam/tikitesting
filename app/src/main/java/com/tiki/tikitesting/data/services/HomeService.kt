package com.tiki.tikitesting.data.services

import com.tiki.tikitesting.data.models.FlashDealResponse
import com.tiki.tikitesting.data.models.HomeBannerResponse
import com.tiki.tikitesting.data.models.QuickLinkResponse
import com.tiki.tikitesting.data.models.Response
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HomeService {
    @GET("v2/home/banners/v2")
    fun getBanner(): Deferred<Response<HomeBannerResponse>>

    @GET("shopping/v2/widgets/quick_link")
    fun getQuickLink(): Deferred<Response<List<QuickLinkResponse>>>

    @GET("v2/widget/deals/hot")
    fun getFlashDeal(): Deferred<Response<FlashDealResponse>>
}