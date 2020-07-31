package com.tiki.tikitesting

import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.tiki.tikitesting.data.base.BasicSingleViewAdapter
import com.tiki.tikitesting.data.base.BindableAdapter
import com.tiki.tikitesting.data.base.InfinityAdapter
import com.tiki.tikitesting.data.models.FlashDealEntity
import com.tiki.tikitesting.data.models.HomeBannerEntity
import com.tiki.tikitesting.data.models.QuickLinkEntity
import com.tiki.tikitesting.data.viewmodel.HomeViewModel
import com.tiki.tikitesting.utils.ItemDecorationList
import com.tiki.tikitesting.utils.getViewModel
import com.tiki.tikitesting.viewholders.BannerItemViewHolder
import com.tiki.tikitesting.viewholders.FlashDealItemViewHolder
import com.tiki.tikitesting.viewholders.QuickLinkItemViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_flash_deal.*


class MainActivity : AppCompatActivity() {

    private var SCROLL_SPEED = 2000L //millisecond

    private lateinit var viewModel: HomeViewModel

    //this field to check one of 2 api (banner, quicklink) is done
    private var isOneOfBothFinished = false

    //check touching to stop auto scroll
    private var isTouchingBanner = false
    private var currentIndex = 0

    //Handler to handler auto swipe
    private val handler = Handler()


    //runnable to run task auto swipe
    private var runnableAutoScroll = Runnable {
        rvBanner?.run {
            if (!isTouchingBanner) {
                currentIndex++
                smoothScrollToPosition(currentIndex)
            }
        }
        startLoopAutoScroll()
    }

    private var runnableDelayAutoScrollEnable = Runnable {
        isTouchingBanner = false
        currentIndex = (rvBanner.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_main)
        viewModel = getViewModel(HomeViewModel::class.java)
        initView()
        observerData()
    }

    private fun initView() {

        swRefresh.setOnRefreshListener {
            viewModel.getBannerAndQuickLink()
            swRefresh.isRefreshing = false
        }

        rvBanner.run {
            addItemDecoration(
                ItemDecorationList(
                    resources.getDimensionPixelSize(R.dimen.dp10),
                    RecyclerView.HORIZONTAL
                )
            )
            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                }

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_DOWN -> isTouchingBanner = true
                        MotionEvent.ACTION_MOVE -> isTouchingBanner = true
                        MotionEvent.ACTION_UP -> {
                            handler.removeCallbacks(runnableDelayAutoScrollEnable)
                            handler.postDelayed(runnableDelayAutoScrollEnable, 3000)
                        }
                    }
                    return false
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

                }
            })
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = InfinityAdapter<HomeBannerEntity>(
                R.layout.item_banner,
                BannerItemViewHolder::class
            )

            //Add snap to banner list
            LinearSnapHelper().attachToRecyclerView(this)
        }


        rvQuickLinkList.run {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
            adapter = BasicSingleViewAdapter<QuickLinkEntity>(
                R.layout.item_quick_link,
                QuickLinkItemViewHolder::class
            )
        }


        rvFlashDeal.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = BasicSingleViewAdapter<FlashDealEntity>(
                R.layout.item_flash_deal,
                FlashDealItemViewHolder::class
            )
        }
    }

    private fun observerData() {
        viewModel.listBanner.observe(this, Observer {
            if (isOneOfBothFinished) {
                viewModel.getFlashDeal()
            }
            isOneOfBothFinished = true
            it?.run {
                if (isNotEmpty()) {
                    (rvBanner.adapter as BindableAdapter<HomeBannerEntity>).setData(this)
                    rvBanner.post { rvBanner.scrollToPosition(Int.MAX_VALUE / 2) }
                    currentIndex = Int.MAX_VALUE / 2
                    startLoopAutoScroll()
                }
            }
        })

        viewModel.listQuickLink.observe(this, Observer {
            if (isOneOfBothFinished) {
                viewModel.getFlashDeal()
            }
            isOneOfBothFinished = true
            it?.run {
                if (isNotEmpty()) {
                    (rvQuickLinkList.adapter as BindableAdapter<QuickLinkEntity>).setData(this)
                }
            }

        })

        viewModel.listFlashDeal.observe(this, Observer {
            it?.run {
                if (isNotEmpty()) {
                    (rvFlashDeal.adapter as BindableAdapter<FlashDealEntity>).setData(this)
                }
            }
        })

        viewModel.loadingBanner.observe(this, Observer {
            when (it) {
                true -> pgBannerLoad.post { pgBannerLoad.visibility = View.VISIBLE }
                false -> pgBannerLoad.post { pgBannerLoad.visibility = View.INVISIBLE }
            }
        })

        viewModel.loadingFlashDeal.observe(this, Observer {
            when (it) {
                true -> pgFlashDealList.post { pgFlashDealList.visibility = View.VISIBLE }
                false -> pgFlashDealList.post { pgFlashDealList.visibility = View.INVISIBLE }
            }
        })
    }

    private fun startLoopAutoScroll() {
        handler.postDelayed(runnableAutoScroll, SCROLL_SPEED)
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnableAutoScroll)
        super.onDestroy()
    }

}
