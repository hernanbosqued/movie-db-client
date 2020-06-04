package com.hernanbosqued.movie_db_client

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.Constants


class ItemViewHolder(view: View, itemType: ITEM_TYPE) : BaseViewHolder<ResultModel>(view) {
    private val titleTextView: TextView = view.findViewById<View>(R.id.title) as TextView
    private val voteTextView: TextView = view.findViewById<View>(R.id.vote) as TextView
    private val backgroundImageView: ImageView = view.findViewById<View>(R.id.background) as ImageView

    override fun bind(model: ResultModel) {
        titleTextView.text = model.name
        voteTextView.text = model.voteAverage

        val imagePath = Constants.IMAGE_BASE_URL + model.posterPath

        Glide
            .with(itemView.context)
            .load(imagePath)
            .transform(CenterCrop(), RoundedCorners(itemView.resources.getDimension(R.dimen.rounded_size).toInt()))
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