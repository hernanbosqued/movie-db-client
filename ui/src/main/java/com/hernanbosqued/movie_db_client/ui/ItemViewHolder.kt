package com.hernanbosqued.movie_db_client.ui

import android.annotation.SuppressLint
import android.view.MotionEvent.ACTION_CANCEL
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import androidx.core.content.ContextCompat
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.repo.Constants
import kotlinx.android.synthetic.main.layout_item.view.no_image
import kotlinx.android.synthetic.main.layout_item.view.poster
import kotlinx.android.synthetic.main.layout_item.view.progress
import kotlinx.android.synthetic.main.layout_item.view.ranking
import kotlinx.android.synthetic.main.layout_item.view.title
import kotlinx.android.synthetic.main.layout_item.view.type

class ItemViewHolder(view: View, private val listener: ItemListener) :
    BaseViewHolder<CarouselItemModel>(view) {

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(model: CarouselItemModel) {
        Utils.setAnimation(itemView)
        itemView.title.text = model.title
        itemView.ranking.text = model.ranking

        itemView.type.text = Utils.getSpan(
            model.type!!,
            if (model.hasVideo) itemView.resources.getString(R.string.has_video) else "",
            ContextCompat.getColor(itemView.context, R.color.colorAccent)
        )

        itemView.setOnTouchListener { v: View, event ->
            when (event.action) {
                ACTION_DOWN ->
                    v.animate()
                        .translationX(10F)
                        .translationY(10F)
                        .translationZ(v.elevation + 100F).start()
                ACTION_CANCEL,
                ACTION_UP ->
                    v.animate()
                        .translationX(0F)
                        .translationY(0F)
                        .translationZ(v.elevation).start()

            }
            false
        }
        itemView.setOnClickListener {
            listener.onItemClicked(model)
        }

        model.path.let { path ->
            itemView.progress.visibility = View.VISIBLE
            val absolutePath = Constants.IMAGE_BASE_URL + path

            Utils.setImage(
                itemView.poster,
                itemView.progress,
                itemView.no_image,
                absolutePath,
                roundedCorners = true
            )
        }
    }
}