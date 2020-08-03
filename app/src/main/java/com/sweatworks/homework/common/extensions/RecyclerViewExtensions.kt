package com.sweatworks.homework.common.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

inline fun RecyclerView.paginate(crossinline loadMore: () -> Unit) {
    val linearLayoutManager = layoutManager as LinearLayoutManager

    addOnScrollStateChangedListener { _, _ ->
        if (adapter!!.itemCount > 0) {
            val lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()
            val isNearlyEnding = lastVisiblePosition >= (adapter?.itemCount ?: Int.MAX_VALUE) / 1.5
            if (isNearlyEnding) loadMore()
        }
    }
}

@PublishedApi
internal inline fun RecyclerView.addOnScrollStateChangedListener(
    crossinline function: (newState: Int, RecyclerView.OnScrollListener) -> Unit
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            function(newState, this)
        }
    })
}