package com.cxsplay.appkotlin.widget.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by CxS on 2019/5/24 14:49.
 * Description:
 */
class SimpleDividerItemDecoration(var offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val lm = parent.layoutManager
        if (lm is GridLayoutManager) {
            val spanCount = lm.spanCount
            val spanSizeLookup = lm.spanSizeLookup
            val childCount = parent.adapter?.itemCount
            val childAdapterPosition = parent.getChildAdapterPosition(view)
            if (spanCount == spanSizeLookup.getSpanSize(childAdapterPosition) && ((childCount?.minus(1)) == childAdapterPosition)) {
                outRect.set(0, offset, 0, 0)
            }
        } else if (lm is LinearLayoutManager) {
            if (LinearLayoutManager.VERTICAL == lm.orientation) {
                outRect.set(0, offset, 0, 0)
            }
        }
    }
}
