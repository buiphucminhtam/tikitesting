package com.tiki.tikitesting.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tiki.tikitesting.data.base.BaseViewModel
import com.tiki.tikitesting.data.models.*
import com.tiki.tikitesting.data.repositories.HomeRepository

class HomeViewModel : BaseViewModel() {
    private val repo by lazy { HomeRepository.INSTANCE }

    var listBanner = MutableLiveData<List<HomeBannerEntity>>()
    var listQuickLink = MutableLiveData<List<QuickLinkEntity>>()
    var listFlashDeal = MutableLiveData<List<FlashDealEntity>>()

    var loadingBanner = MutableLiveData<Boolean>()
    var loadingFlashDeal = MutableLiveData<Boolean>()

    init {
       getBannerAndQuickLink()
    }

    fun getBannerAndQuickLink() {
        getBanners()
        getQuickLink()
    }

    private fun getBanners() {
        async({
            //Handler error api
            Log.e("HomeViewModel", it.message)
            listBanner.postValue(arrayListOf())
        }) {
            loadingBanner.postValue(true)
            val response = repo.getBanner()
            response.data?.run {
                listBanner.postValue(toListHomeBannerEntity())
            }
        }
    }

    private fun getQuickLink(){
        async ({
            //Handler error api
            Log.e("HomeViewModel", it.message)
            listQuickLink.postValue(arrayListOf())
        }){
            val response = repo.getQuickLink()
            response.data?.run {
                val arrayListQuickLink = arrayListOf<QuickLinkEntity>()
                var maxSize = 0
                forEach { item -> if(item.size > maxSize) maxSize = item.size }

                for (i in 0 until maxSize) {
                    forEach { item -> if(i < item.size) arrayListQuickLink.add(item[i].toQuickLinkEntity())}

                }

                listQuickLink.postValue(arrayListQuickLink)
            }
        }
    }

    fun getFlashDeal() {
        async  ({
            //Handler error api
            Log.e("HomeViewModel", it.message)
            listFlashDeal.postValue(arrayListOf())
            loadingFlashDeal.postValue(false)
        }){
            loadingBanner.postValue(false)
            loadingFlashDeal.postValue(true)
            val response = repo.getFlashDeal()
            response.data?.run {
                listFlashDeal.postValue(toListFlashDealEntity())
            }
            loadingFlashDeal.postValue(false)
        }
    }
}