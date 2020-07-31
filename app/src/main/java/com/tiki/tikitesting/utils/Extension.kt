package com.tiki.tikitesting.utils

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

/**
 * Get ViewModel from Fragment
 */
internal fun <VM : ViewModel> Fragment.getViewModel(
    vmClass: Class<VM>,
    vmFactory: ViewModelProvider.Factory? = null
): VM {
    return vmFactory?.let { ViewModelProviders.of(this, it).get(vmClass) } ?: ViewModelProviders.of(
        this
    ).get(vmClass)
}

/**
 * Get ViewModel from Fragment with key
 */
internal fun <VM : ViewModel> Fragment.getViewModel(
    key: String,
    vmClass: Class<VM>,
    vmFactory: ViewModelProvider.Factory? = null
): VM {
    return vmFactory?.let { ViewModelProviders.of(this, it).get(key, vmClass) }
        ?: ViewModelProviders.of(this).get(
            key,
            vmClass
        )
}

/**
 * Get ViewModel from Activity
 */
internal fun <VM : ViewModel> FragmentActivity.getViewModel(
    vmClass: Class<VM>,
    vmFactory: ViewModelProvider.Factory? = null
): VM {
    return vmFactory?.let { ViewModelProviders.of(this, it).get(vmClass) } ?: ViewModelProviders.of(
        this
    ).get(vmClass)
}

/**
 * Quick inflate view in fragment [onCreate] function
 */
internal fun Fragment.inflateView(
    viewId: Int,
    viewGroup: ViewGroup?,
    attachToRoot: Boolean = false
): View {
    return layoutInflater.inflate(viewId, viewGroup, attachToRoot)
}

internal fun <T> LiveData<T>.observeOnce(observer: Observer<T>) {
    observeForever(object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}
