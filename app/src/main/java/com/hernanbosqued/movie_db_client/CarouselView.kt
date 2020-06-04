package com.hernanbosqued.movie_db_client

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.model.ResultModel

@SuppressLint("ViewConstructor")
class CarouselView(context: Context, client: Client, name: String) : LinearLayout(context), ReachLastItemListener, CarouselContract.View {

    private var adapter: ItemsAdapter = ItemsAdapter()
    private var presenter: CarouselPresenter = CarouselPresenter(client)

    init {
        adapter = ItemsAdapter()
        adapter.reachLastItemListener = this
        orientation = VERTICAL

        val params = MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.topMargin = resources.getDimension(R.dimen.margin16).toInt()
        params.leftMargin = resources.getDimension(R.dimen.spacing_size).toInt()
        val textView = TextView(ContextThemeWrapper(getContext(), R.style.nameStyle), null, 0)
        textView.text = name
        textView.layoutParams = params
        addView(textView)

        val recyclerView = RecyclerView(context)
        params.leftMargin = 0
        recyclerView.layoutParams = params
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
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