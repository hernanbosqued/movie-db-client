package com.hernanbosqued.movie_db_client

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.hernanbosqued.domain.model.ResultModel


class ItemViewHolder(view: View, itemType: ITEM_TYPE) : BaseViewHolder<ResultModel>(view) {
    private val titleTextView: TextView = view.findViewById<View>(R.id.title) as TextView
    private val voteTextView: TextView = view.findViewById<View>(R.id.vote) as TextView
    private val backgroundImageView: ImageView = view.findViewById<View>(R.id.background) as ImageView

    override fun bind(model: ResultModel) {
        titleTextView.text = model.originalTitle
        voteTextView.text = model.voteAverage
        Glide
            .with(itemView.context)
            .load("https://image.tmdb.org/t/p/w300/" + model.backdropPath )
            .into(backgroundImageView)
    }

    init {
        val color: Int = when (itemType) {
            ITEM_TYPE.ROW_ODD -> ContextCompat.getColor(view.context, R.color.light_gray)
            ITEM_TYPE.ROW_EVEN -> ContextCompat.getColor(view.context, R.color.gray)
        }
        view.setBackgroundColor(color)
    }
}