package com.tiki.tikitesting.viewholders

import android.view.View
import com.tiki.tikitesting.data.base.BaseViewHolder
import com.tiki.tikitesting.data.models.FlashDealEntity
import com.tiki.tikitesting.utils.PicassoUtil
import kotlinx.android.synthetic.main.item_flash_deal.view.*

class FlashDealItemViewHolder(itemView: View) :
    BaseViewHolder<FlashDealEntity>(itemView) {
    override fun initViews() {

    }

    override fun bind() {
        data?.run {
            if (imageUrl.isNotBlank())
                PicassoUtil.get(PicassoUtil.HOME).load(imageUrl).into(containerView.ivFlashDealItem)
            containerView.tvPriceFlashDealItem.text = price
            containerView.pgFlashDealItem.progress = (qtyOrdered * 100 / qty)
            containerView.tvDiscountPercentFlashDealItem.text = "-$discountPercent%"
            when (qtyOrdered) {
                0 -> containerView.tvProgressStatusFlashDealItem.text = "Vừa mở bán"
                else -> containerView.tvProgressStatusFlashDealItem.text = "Đã bán $qtyOrdered"
            }
        }
    }
}