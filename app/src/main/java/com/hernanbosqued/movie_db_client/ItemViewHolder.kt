package com.hernanbosqued.movie_db_client

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.Constants
import kotlinx.android.synthetic.main.layout_item.view.*


class ItemViewHolder(view: View) : BaseViewHolder<ResultModel>(view) {

    override fun bind(model: ResultModel) {
        itemView.title.text = model.name
        itemView.ranking.text = model.voteAverage

        val imagePath = Constants.IMAGE_BASE_URL + model.posterPath

        Glide
            .with(itemView.context)
            .load(imagePath)
            .transform(CenterCrop(), RoundedCorners(itemView.resources.getDimension(R.dimen.rounded_size).toInt()))
            .into(itemView.poster)

        setAnimation()
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