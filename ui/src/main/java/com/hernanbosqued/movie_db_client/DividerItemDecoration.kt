package com.hernanbosqued.movie_db_client

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(@NonNull context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable = context.resources.getDrawable(R.drawable.divider)

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left: Int = parent.paddingLeft
        val right: Int = parent.width - parent.paddingRight
        val childCount: Int = parent.childCount

        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
    }

}