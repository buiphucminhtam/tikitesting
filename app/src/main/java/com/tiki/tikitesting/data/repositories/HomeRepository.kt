package com.tiki.tikitesting.data.repositories

import com.tiki.tikitesting.data.api.API
import com.tiki.tikitesting.data.models.FlashDealResponse
import com.tiki.tikitesting.data.models.HomeBannerResponse
import com.tiki.tikitesting.data.models.QuickLinkResponse
import com.tiki.tikitesting.data.models.Response


class HomeRepository {

    companion object {
        val INSTANCE = HomeRepository()
    }

    suspend fun getBanner(): Response<HomeBannerResponse> {
        return API.homeService.getBanner().await()
    }

    suspend fun getQuickLink(): Response<List<QuickLinkResponse>> {
        return API.homeService.getQuickLink().await()
    }

    suspend fun getFlashDeal(): Response<FlashDealResponse> {
        return API.homeService.getFlashDeal().await()
    }

}