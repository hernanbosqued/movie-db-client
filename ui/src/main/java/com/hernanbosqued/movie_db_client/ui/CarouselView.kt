package com.hernanbosqued.movie_db_client.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernanbosqued.movie_db_client.domain.CarouselClient
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import kotlinx.android.synthetic.main.layout_carrousel.view.*

@SuppressLint("ViewConstructor")
class CarouselView(context: Context, private val listener: CarouselListeners) : ConstraintLayout(context), com.hernanbosqued.movie_db_client.presenter.CarouselContract.View, ScrollListener, LifecycleObserver {

    private val client = CarouselClient(
        RepositoryImpl(context), ResourcesRepositoryImpl(context)
    )
    private val presenter =
        com.hernanbosqued.movie_db_client.presenter.CarouselPresenter(
            this,
            client
        )
    private val adapter = ItemsAdapter(this, listener)

    init {
        inflate(context, R.layout.layout_carrousel, this)

        prepareRecyclerView()

        background = ContextCompat.getDrawable(context, R.drawable.placeholder)
        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.placeholder_light))

        setOnClickListener {
            presenter.onCarouselClicked()
        }
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
        presenter.setModel(model)
    }

    private fun prepareRecyclerView() {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        carousel.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.isItemPrefetchEnabled = true
        layoutManager.recycleChildrenOnDetach = true
        carousel.layoutManager = layoutManager
        carousel.adapter = adapter
    }

    override fun setCarouselInfo(title: String, page: Int, totalPages: Int, totalResults: Int, results: Int) {
        title_text.text = title
        page_text.text = Utils.getSpan(context.getString(R.string.page), page.toString(), ContextCompat.getColor(context!!, R.color.colorAccent))
        results_text.text = Utils.getSpan(context.getString(R.string.results), results.toString(), ContextCompat.getColor(context!!, R.color.colorAccent))
        total_pages_text.text = Utils.getSpan(context.getString(R.string.total_pages), totalPages.toString(), ContextCompat.getColor(context!!, R.color.colorAccent))
        total_results_text.text = Utils.getSpan(context.getString(R.string.total_results), totalResults.toString(), ContextCompat.getColor(context!!, R.color.colorAccent))
    }

    override fun showCarouselData(model: CarouselModel) {
        listener.onCarouselClicked(model)
    }

    override fun addData(data: MutableList<CarouselItemModel>) {
        adapter.add(data)
    }

    override fun showMessage(message: String) {
        Toast.makeText(rootView.context, message, Toast.LENGTH_LONG).show()
    }

    override fun onLastItemReached() {
        presenter.load(false)
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.INVISIBLE
    }

    override fun showEmpty() {
        title_text.visibility = View.GONE
        page_text .visibility = View.GONE
        results_text.visibility = View.GONE
        total_results_text.visibility = View.GONE
        total_pages_text.visibility = View.GONE
        carousel.visibility = View.GONE
        empty_view.visibility = View.VISIBLE
    }

    override fun showEmpty(error: String) {
        showEmpty()
        empty_view.text = error
    }

    override fun hideEmpty() {
        title_text.visibility = View.VISIBLE
        page_text .visibility = View.VISIBLE
        results_text.visibility = View.VISIBLE
        total_results_text.visibility = View.VISIBLE
        total_pages_text.visibility = View.VISIBLE
        carousel.visibility = View.VISIBLE
        empty_view.visibility = View.INVISIBLE
    }
}