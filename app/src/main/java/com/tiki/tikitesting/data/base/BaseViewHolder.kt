package com.tiki.tikitesting.data.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView;
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel

/**
 * A base class of [RecyclerView.ViewHolder], suit for [BaseRVAdapter], can use separate with other Adapter
 * Extended class mustn't add anything to constructor
 */
abstract class BaseViewHolder<TYPE>(itemView: View) :
        RecyclerView.ViewHolder(itemView), IViewHolder<TYPE>, LayoutContainer {

    public val TAG:String = javaClass.simpleName

    override var viewType: Int = 0

    override var data: TYPE? = null

    override val containerView: View
        get() = itemView

    init {
        this.initViews()
    }

    /**
     * Init views config and event like onClick ...
     */
    abstract fun initViews()

    /**
     * To make sure the ViewHolder have data to show and return
     */
    override fun set(data: TYPE) {
        this.data = data
    }

}

/**
 * Basic interface of ViewHolder, easy to use and understand, just set and bind.
 */
interface IViewHolder<TYPE> {
    /**
     * Data is data, no need to explain
     */
    var data: TYPE?

    /**
     * For multi viewType recyclerView
     */
    var viewType: Int

    /**
     * Method call in [BaseRVAdapter] to set data for ViewHolder
     */
    fun set(data: TYPE)

    /**
     * Method call in [BaseRVAdapter] to start binding data for ViewHolder
     */
    fun bind()
}