package com.hernanbosqued.movie_db_client

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.ITEM_TYPE.Companion.valueOf


class ItemsAdapter : BaseAdapter<ResultModel, ItemViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(view, valueOf(viewType))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        setAnimation(holder.itemView)
    }

    private fun setAnimation(itemView: View) {
        val animatorSet = AnimatorSet()

        itemView.alpha = 0f
        itemView.translationX = itemView.x + 200

        val translate: ObjectAnimator = ObjectAnimator.ofFloat(itemView, "translationX", 0f)
        val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)

        animatorSet.duration = 300
        animatorSet.playTogether(translate, alpha)
        animatorSet.start()
    }
}