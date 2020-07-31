package com.tiki.tikitesting.viewholders

import android.view.View
import com.tiki.tikitesting.data.base.BaseViewHolder
import com.tiki.tikitesting.data.models.HomeBannerEntity
import com.tiki.tikitesting.utils.PicassoUtil
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerItemViewHolder(itemView: View) :
    BaseViewHolder<HomeBannerEntity>(itemView) {
    override fun initViews() {

    }

    override fun bind() {
        data?.run {
            if (imageUrl.isNotBlank())
                PicassoUtil.get(PicassoUtil.HOME).load(imageUrl).into(itemView.ivBannerItem)
        }
    }
}