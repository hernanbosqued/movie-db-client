package com.hernanbosqued.movie_db_client

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.domain.model.ResultModel

class CarouselView(context: Context) : LinearLayout(context), BaseAdapterListener, CarouselContract.View {

    private var adapter: ItemsAdapter = ItemsAdapter()
    private var presenter: CarouselPresenter = CarouselPresenter()

    init {
        adapter = ItemsAdapter()
        adapter.listener = this
        orientation = VERTICAL
        val textView = TextView(context)
        textView.text = "PROGRAMATICO"
        addView(textView)
        val recyclerView = RecyclerView(context)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        addView(recyclerView)

        presenter.load()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.unbindView()
    }
    override fun onBottom() {
        presenter.loadMore()
    }

    override fun showItems(model: List<ResultModel>) {
        adapter.setData(model)
    }

    override fun showMessage(message: String) {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showEmpty() {
    }

    override fun hideEmpty() {
    }
}