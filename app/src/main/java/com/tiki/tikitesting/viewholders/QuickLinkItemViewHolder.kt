package com.tiki.tikitesting.viewholders

import android.view.View
import com.tiki.tikitesting.data.base.BaseViewHolder
import com.tiki.tikitesting.data.base.RVClickListener
import com.tiki.tikitesting.data.models.QuickLinkEntity
import com.tiki.tikitesting.utils.PicassoUtil
import kotlinx.android.synthetic.main.item_quick_link.view.*

class QuickLinkItemViewHolder(itemView: View) :
    BaseViewHolder<QuickLinkEntity>(itemView) {
    override fun initViews() {

    }

    override fun bind() {
        data?.run {
            if (imageUrl.isNotBlank())
                PicassoUtil.get(PicassoUtil.HOME).load(imageUrl).into(containerView.ivQuickLinkItem)
            containerView.tvQuickLinkItem.text = title
        }
    }
}