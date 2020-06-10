package com.hernanbosqued.movie_db_client

import android.view.View
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.Constants
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemViewHolder(view: View, private val listener: ItemListener) : BaseViewHolder<ResultModel>(view) {

    override fun bind(model: ResultModel) {
        model.setType()

        itemView.title.text = model.type.geLabel(model)
        itemView.ranking.text = model.voteAverage

        val imagePath = Constants.IMAGE_BASE_URL + model.posterPath

        itemView.progress.visibility = View.VISIBLE
        Utils.setImage(itemView.poster, itemView.progress, imagePath, true, true)
        itemView.setOnClickListener { listener.onItemClicked(model) }
    }
}