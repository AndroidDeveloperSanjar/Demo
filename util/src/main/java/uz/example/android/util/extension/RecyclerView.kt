package uz.example.android.util.extension

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

inline fun RecyclerView.onPartiallyVisibleItems(
    visibilityThreshold: Float,
    crossinline action: (Int) -> Unit
) {
    val scrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
        val lm = layoutManager
        if (lm is LinearLayoutManager) {
            val firstPos = lm.findFirstVisibleItemPosition()
            val lastPos = lm.findLastVisibleItemPosition()
            for (i in firstPos..lastPos) {
                if (findViewHolderForAdapterPosition(i)?.itemView.listItemVisiblePercent() > visibilityThreshold) {
                    action(i)
                }
            }
        }
    }
    doOnAttach {
        viewTreeObserver.addOnScrollChangedListener(scrollChangedListener)
        doOnDetach {
            viewTreeObserver?.removeOnScrollChangedListener(scrollChangedListener)
        }
    }
}

fun View?.listItemVisiblePercent(): Double =
    if (this != null && isVisible && parent != null && alpha >= .5f) {
        val rect = Rect()
        if (getGlobalVisibleRect(rect)) {
            (rect.width() * rect.height()).toDouble() / (width * height).toDouble()
        } else {
            .0
        }
    } else {
        .0
    }

fun RecyclerView.vertical(rowCount: Int = 1) {
    layoutManager = GridLayoutManager(context, rowCount.coerceAtLeast(1))
}

fun RecyclerView.horizontal(rowCount: Int = 1) {
    layoutManager = GridLayoutManager(
        context,
        rowCount.coerceAtLeast(1),
        GridLayoutManager.HORIZONTAL,
        false
    )
}
