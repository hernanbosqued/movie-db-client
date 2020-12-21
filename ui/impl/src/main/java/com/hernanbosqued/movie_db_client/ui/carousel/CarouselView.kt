package com.hernanbosqued.movie_db_client.ui.carousel

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.ui.R
import com.hernanbosqued.movie_db_client.ui.SpacingItemDecoration
import com.hernanbosqued.movie_db_client.ui.Utils
import com.hernanbosqued.movie_db_client.ui.databinding.LayoutCarrouselBinding
import io.reactivex.disposables.CompositeDisposable


@SuppressLint("ViewConstructor")
class CarouselView(context: Context, val model: CarouselModel, private val listener: CarouselListeners) : ConstraintLayout(context), ScrollListener, LifecycleObserver {

    private val viewModel: CarouselViewModel = CarouselViewModel()

    private val compositeDisposable = CompositeDisposable()

    private val binding: LayoutCarrouselBinding by lazy {
        LayoutCarrouselBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private val adapter = ItemsAdapter(this, listener)

    init {
        background = ContextCompat.getDrawable(context, R.drawable.placeholder)
        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.placeholder_light))

        setOnClickListener {
            listener.onCarouselClicked(model)
        }

        prepareRecyclerView()
        binding.viewModel = viewModel
        viewModel.load(model, true)
    }

    private fun prepareRecyclerView() {
        binding.carousel.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.isItemPrefetchEnabled = true
        layoutManager.recycleChildrenOnDetach = true
        binding.carousel.layoutManager = layoutManager
        binding.carousel.adapter = adapter
    }

    private fun handleState(state: CarouselState) {
        when (state) {
            is CarouselState.Info -> setInfo(state.title, state.page, state.totalPages, state.totalResults, state.results)
            is CarouselState.Message -> showMessage(state.data)
            is CarouselState.Data -> addData(state.data)
        }
    }

    private fun setInfo(title: String, page: Int, totalPages: Int, totalResults: Int, results: Int) {
        binding.titleText.text = title
        binding.pageText.text = Utils.getSpan(context.getString(R.string.page), page.toString(), ContextCompat.getColor(context, R.color.colorAccent))
        binding.resultsText.text = Utils.getSpan(context.getString(R.string.results), results.toString(), ContextCompat.getColor(context, R.color.colorAccent))
        binding.totalPagesText.text = Utils.getSpan(context.getString(R.string.total_pages), totalPages.toString(), ContextCompat.getColor(context, R.color.colorAccent))
        binding.totalResultsText.text = Utils.getSpan(context.getString(R.string.total_results), totalResults.toString(), ContextCompat.getColor(context, R.color.colorAccent))
    }

    private fun addData(data: MutableList<CarouselItemModel>) {
        adapter.add(data)
    }

    override fun onLastItemReached() {
        viewModel.loadMore()
    }

    private fun showMessage(message: String) {
        binding.alert.visibility = VISIBLE
        binding.carousel.visibility = GONE
        binding.alert.text = message
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        compositeDisposable.add(viewModel.state().subscribe(this::handleState))
    }

    override fun onDetachedFromWindow() {
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
        super.onDetachedFromWindow()
    }
}