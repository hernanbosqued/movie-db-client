package com.hernanbosqued.movie_db_client

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.Constants
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemViewHolder(view: View, private val listener: ItemListener) : BaseViewHolder<ResultModel>(view) {

    override fun bind(model: ResultModel) {
        itemView.title.text = model.name
        itemView.ranking.text = model.voteAverage

        val imagePath = Constants.IMAGE_BASE_URL + model.posterPath

        itemView.progress.visibility = View.VISIBLE

        Glide
            .with(itemView.context as Activity)
            .load(imagePath)
            .transform(CenterCrop(), RoundedCorners(itemView.resources.getDimension(R.dimen.rounded_size).toInt()))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    itemView.progress.visibility = View.INVISIBLE
                    return false
                }
            })
            .into(itemView.poster)

        setAnimation()

        itemView.setOnClickListener { listener.onItemClicked(model) }
    }

    private fun setAnimation() {
        val animatorSet = AnimatorSet()

        itemView.alpha = 0f
        itemView.translationY = itemView.y - 300

        val translate: ObjectAnimator = ObjectAnimator.ofFloat(itemView, "translationY", 0f)
        val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)

        animatorSet.duration = 500
        animatorSet.playTogether(translate, alpha)
        animatorSet.start()
    }
}