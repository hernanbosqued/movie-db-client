package com.hernanbosqued.movie_db_client.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

internal object Utils {

    @SuppressLint("CheckResult")
    fun setImage(view: ImageView, progress: View?, path: String, showAnimation: Boolean, roundedCorners: Boolean) {

        progress?.let { it.visibility = View.VISIBLE }

        val builder = Glide
            .with(view.context as Activity)
            .load(path)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progress?.let { it.visibility = View.INVISIBLE }
                    return false
                }
            })

        if (roundedCorners) {
            builder.transform(CenterCrop(), RoundedCorners(view.resources.getDimension(R.dimen.rounded_size).toInt()))
        } else {
            builder.transform(CenterCrop())
        }

        builder.into(view)

        if (showAnimation) {
            setAnimation(view)
        }
    }

    private fun setAnimation(view: ImageView) {
        val animatorSet = AnimatorSet()

        view.alpha = 0f
        view.translationY = view.y - 300

        val translate: ObjectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f)
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 1f)

        animatorSet.duration = 500
        animatorSet.playTogether(translate, alpha)
        animatorSet.start()
    }
}