package com.hernanbosqued.movie_db_client.ui

import android.view.View
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.repo.Constants
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemViewHolder(view: View, private val listener: ItemListener) : BaseViewHolder<CarouselItemModel>(view) {

    override fun bind(model: CarouselItemModel) {
        Utils.setAnimation(itemView)
        itemView.title.text = model.title
        itemView.ranking.text = model.ranking
        itemView.type.text = model.type
        itemView.setOnClickListener { listener.onItemClicked(model) }

        model.path.let { path ->
            itemView.progress.visibility = View.VISIBLE
            val absolutePath = Constants.IMAGE_BASE_URL + path

            Utils.setImage(itemView.poster, itemView.progress, itemView.no_image, absolutePath, roundedCorners = true)
        }
    }
}