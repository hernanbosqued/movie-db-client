package com.hernanbosqued.movie_db_client.ui

import android.view.View
import com.hernanbosqued.movie_db_client.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.repo.Constants
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemViewHolder(view: View, private val listener: ItemListener) : BaseViewHolder<ResultModel>(view) {

    override fun bind(model: ResultModel) {
        model.setType()

        itemView.title.text = model.type.geLabel(model)
        itemView.ranking.text = model.voteAverage
        itemView.setOnClickListener { listener.onItemClicked(model) }

        model.posterPath?.let {
            val absolutePath = Constants.IMAGE_BASE_URL + it
            itemView.progress.visibility = View.VISIBLE
            Utils.setImage(itemView.poster, itemView.progress, itemView.no_image, absolutePath, showAnimation = true, roundedCorners = true)
        }?:run {
            itemView.no_image.visibility = View.VISIBLE
        }

    }
}