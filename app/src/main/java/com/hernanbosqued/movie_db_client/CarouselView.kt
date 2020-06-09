package com.hernanbosqued.movie_db_client

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.movie_db_client.adapter.ItemsAdapter
import kotlinx.android.synthetic.main.layout_carrousel.view.*


@SuppressLint("ViewConstructor")
class CarouselView(context: Context, private val listener: CarouselListeners) : ConstraintLayout(context), CarouselContract.View, ScrollListener {
    private var adapter = ItemsAdapter(this, listener)
    private lateinit var presenter: CarouselPresenter

    init {
        inflate(context, R.layout.layout_carrousel, this)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        container.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        container.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        container.adapter = adapter

        setOnClickListener {
            presenter.onCarouselClicked()
        }
    }

    fun bind(model: CarouselModel) {
        carousel_title.text = model.title
        presenter = CarouselPresenter(model, this)
        presenter.load()
    }

    override fun showCarouselData(model: ListModel) {
        listener.onCarouselClicked(model)
    }

    override fun addData(model: ListModel) {
        adapter.add(model.results)
    }

    override fun showMessage(message: String) {
        Toast.makeText(rootView.context, message, Toast.LENGTH_SHORT).show()
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