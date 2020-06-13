package com.hernanbosqued.movie_db_client.ui

import android.view.View
import com.hernanbosqued.movie_db_client.domain.Visitor
import com.hernanbosqued.movie_db_client.domain.model.MovieResultModel
import com.hernanbosqued.movie_db_client.domain.model.PersonResultModel
import com.hernanbosqued.movie_db_client.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.domain.model.TVResultModel
import com.hernanbosqued.movie_db_client.repo.Constants
import kotlinx.android.synthetic.main.layout_item.view.*
import java.util.*

class ItemViewHolder(view: View, private val listener: ItemListener) : BaseViewHolder<ResultModel>(view), Visitor {

    override fun accept(visitable: TVResultModel) {
        itemView.title.text = visitable.name

        visitable.posterPath?.let { path ->
            itemView.progress.visibility = View.VISIBLE
            val absolutePath = Constants.IMAGE_BASE_URL + path
            Utils.setImage(itemView.poster, itemView.progress, itemView.no_image, absolutePath, showAnimation = true, roundedCorners = true)
        } ?: run {
            itemView.no_image.visibility = View.VISIBLE
        }
    }

    override fun accept(visitable: MovieResultModel) {
        itemView.title.text = visitable.title

        visitable.posterPath?.let { path ->
            itemView.progress.visibility = View.VISIBLE
            val absolutePath = Constants.IMAGE_BASE_URL + path
            Utils.setImage(itemView.poster, itemView.progress, itemView.no_image, absolutePath, showAnimation = true, roundedCorners = true)
        } ?: run {
            itemView.no_image.visibility = View.VISIBLE
        }
    }

    override fun accept(visitable: PersonResultModel) {
        itemView.title.text = visitable.name

        visitable.profilePath?.let { path ->
            itemView.progress.visibility = View.VISIBLE
            val absolutePath = Constants.IMAGE_BASE_URL + path
            Utils.setImage(itemView.poster, itemView.progress, itemView.no_image, absolutePath, showAnimation = true, roundedCorners = true)
        } ?: run {
            itemView.no_image.visibility = View.VISIBLE
        }
    }


    @ExperimentalStdlibApi
    override fun accept(visitable: ResultModel) {
        itemView.ranking.text = visitable.ranking
        itemView.type.text = visitable.getMediaType().value.capitalize(Locale.ROOT)
        itemView.setOnClickListener { listener.onItemClicked(visitable) }
    }

    override fun bind(model: ResultModel) {
        model.visit(this)
    }
}