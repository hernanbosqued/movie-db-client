package com.hernanbosqued.movie_db_client

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ResultModel
import kotlinx.android.synthetic.main.layout_carrousel.view.*

@SuppressLint("ViewConstructor")
class CarouselView(context: Context, client: (Int, ClientCallbacks) -> Unit, name: String) : ConstraintLayout(context), ReachLastItemListener, CarouselContract.View {

    private var adapter: ItemsAdapter = ItemsAdapter()
    private var presenter: CarouselPresenter = CarouselPresenter(client)

    init {
        val viewGroup = inflate(getContext(), R.layout.layout_carrousel, this)
        viewGroup.setBackgroundColor(resources.getColor(R.color.gray))
        adapter.reachLastItemListener = this
        viewGroup.name.text = name
        viewGroup.recycler_view.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
        viewGroup.recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewGroup.recycler_view.adapter = adapter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
        presenter.load()
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
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.INVISIBLE
    }

    override fun showEmpty() {
        empty_view.visibility = View.VISIBLE
    }

    override fun hideEmpty(){
        empty_view.visibility = View.INVISIBLE
    }
}