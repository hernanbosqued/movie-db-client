package com.hernanbosqued.movie_db_client

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.model.ResultModel

@SuppressLint("ViewConstructor")
class CarouselView(context: Context, client: Client, title: String) : LinearLayout(context), ReachLastItemListener, CarouselContract.View {

    private var adapter: ItemsAdapter = ItemsAdapter()
    private var presenter: CarouselPresenter = CarouselPresenter(client)

    init {
        adapter = ItemsAdapter()
        adapter.reachLastItemListener = this

        orientation = VERTICAL
        val textView = TextView(context)
        textView.text = title
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
    override fun onLastItemReached() {
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