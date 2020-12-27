package com.hernanbosqued.movie_db_client.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import androidx.core.text.toSpannable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun <T> MutableLiveData<T>.postWithDelay(param: T) {
    Handler(Looper.getMainLooper()).postDelayed({ this.value = param }, 300)
}

fun ObservableBoolean.setWithDelay(param: Boolean) {
    Handler(Looper.getMainLooper()).postDelayed({ this.set(param) }, 300)
}

object Utils {

    fun getSpan(key: String, value: String, accent: Int): Spannable {
        if (key.isNotEmpty()) {
            val span = SpannableStringBuilder(key)
            span.setSpan(ForegroundColorSpan(accent), 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            span.setSpan(StyleSpan(Typeface.BOLD), 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return span.append(" $value").toSpannable()
        }
        return "$key: $value".toSpannable()
    }

    @SuppressLint("CheckResult")
    fun setImage(view: ImageView, progress: View?, noProvided: View?, path: String, roundedCorners: Boolean) {
        progress?.let { it.visibility = View.VISIBLE }

        val builder = Glide
            .with(view.context as Activity)
            .load(path)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    progress?.let { it.visibility = View.INVISIBLE }
                    noProvided?.let { it.visibility = View.INVISIBLE }
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
    }

    fun setAnimation(view: View) {
        val animatorSet = AnimatorSet()

        view.alpha = 0f
        view.translationY = -view.height * 0.5f

        val translate: ObjectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f)
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 1f)

        animatorSet.duration = 500
        animatorSet.playTogether(translate, alpha)
        animatorSet.start()
    }
}