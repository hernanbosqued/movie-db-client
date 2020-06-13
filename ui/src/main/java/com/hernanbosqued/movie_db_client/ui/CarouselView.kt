package com.hernanbosqued.movie_db_client.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernanbosqued.movie_db_client.domain.model.ListModel
import kotlinx.android.synthetic.main.layout_carrousel.view.*
import kotlinx.android.synthetic.main.layout_carrousel.view.progress


@SuppressLint("ViewConstructor")
class CarouselView(context: Context, private val listener: CarouselListeners) : ConstraintLayout(context), CarouselContract.View, ScrollListener, LifecycleObserver {
    private var presenter: CarouselPresenter = CarouselPresenter(this)
    private var adapter = ItemsAdapter(this, listener)

    init {
        inflate(context, R.layout.layout_carrousel, this)

        prepareRecyclerView()

        setOnClickListener {
            presenter.onCarouselClicked()
        }
    }

    private fun prepareRecyclerView() {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        carousel.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
        carousel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        carousel.adapter = adapter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.unbindView()
    }

    fun bind(model: CarouselModel) {
        if (presenter.model == null) {
            presenter.setModel(model)
        }
    }

    override fun setTitle(title: String) {
        carousel_title.text = title
    }

    override fun showCarouselData(model: CarouselModel) {
        listener.onCarouselClicked(model)
    }

    override fun addData(model: ListModel) {
        model.results?.let { adapter.add(it) }
    }

    override fun showMessage(message: String) {
        Toast.makeText(rootView.context, message, Toast.LENGTH_LONG).show()
    }

    override fun onLastItemReached() {
        presenter.load()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.INVISIBLE
    }

    override fun showEmpty() {
        empty_view.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        empty_view.visibility = View.INVISIBLE
    }
}