package com.hernanbosqued.movie_db_client

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(private val spacingSize: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildLayoutPosition(view)
        //outRect.top = spacingSize;
        //outRect.bottom = spacingSize;
        outRect.left = spacingSize
        if (position == parent.childCount) {
            outRect.right = spacingSize
        }

    }
}