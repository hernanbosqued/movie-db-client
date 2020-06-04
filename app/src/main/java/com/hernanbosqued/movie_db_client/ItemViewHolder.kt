package com.hernanbosqued.movie_db_client

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hernanbosqued.domain.model.ResultModel

class ItemViewHolder(view: View, itemType: ITEM_TYPE) : BaseViewHolder<ResultModel>(view) {
    private val headerTextView: TextView = view.findViewById<View>(R.id.header) as TextView
    private val statusTextView: TextView = view.findViewById<View>(R.id.status) as TextView

    override fun bind(model: ResultModel) {
        headerTextView.text = model.originalTitle
        statusTextView.text = model.originalTitle
    }

    init {
        val color: Int = when (itemType) {
            ITEM_TYPE.ROW_ODD -> ContextCompat.getColor(view.context, R.color.light_gray)
            ITEM_TYPE.ROW_EVEN -> ContextCompat.getColor(view.context, R.color.gray)
        }
        view.setBackgroundColor(color)
    }
}