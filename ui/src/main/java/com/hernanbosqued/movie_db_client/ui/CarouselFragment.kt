package com.hernanbosqued.movie_db_client.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.ui.contract.CarouselContract
import com.hernanbosqued.movie_db_client.ui.fragment.BaseFragment
import com.hernanbosqued.movie_db_client.ui.presenter.CarouselPresenter
import kotlinx.android.synthetic.main.layout_carrousel.*
import javax.inject.Inject

@SuppressLint("ViewConstructor")
class CarouselFragment(val model: CarouselModel, private val listener: CarouselListeners) :
    BaseFragment<CarouselFragment.Callbacks>(), CarouselContract.View, ScrollListener, LifecycleObserver {

    @Inject
    lateinit var presenter: CarouselPresenter

    interface Callbacks

    override fun getLayout(): Int {
        return R.layout.layout_carrousel
    }

    override val dummyCallback: Callbacks
        get() = object : CarouselFragment.Callbacks {
        }

    private val adapter = ItemsAdapter(this, listener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()

        this.view?.background = ContextCompat.getDrawable(requireContext(), R.drawable.placeholder)
        this.view?.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.placeholder_light))

        presenter.bindModel(model)
        presenter.load(true)

        this.view?.setOnClickListener {
            presenter.onCarouselClicked()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbindView()
    }

    private fun prepareRecyclerView() {
        carousel.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.isItemPrefetchEnabled = true
        layoutManager.recycleChildrenOnDetach = true
        carousel.layoutManager = layoutManager
        carousel.adapter = adapter
    }

    override fun setInfo(title: String, page: Int, totalPages: Int, totalResults: Int, results: Int) {
        title_text.text = title
        page_text.text = Utils.getSpan(getString(R.string.page), page.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
        results_text.text = Utils.getSpan(getString(R.string.results), results.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
        total_pages_text.text = Utils.getSpan(getString(R.string.total_pages), totalPages.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
        total_results_text.text = Utils.getSpan(getString(R.string.total_results), totalResults.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
    }

    override fun showInfo() {
        page_text.visibility = VISIBLE
        results_text.visibility = VISIBLE
        total_pages_text.visibility = VISIBLE
        total_results_text.visibility = VISIBLE
    }

    override fun hideInfo() {
        page_text.visibility = GONE
        results_text.visibility = GONE
        total_pages_text.visibility = GONE
        total_results_text.visibility = GONE
    }

    override fun showCarouselData(model: CarouselModel) {
        listener.onCarouselClicked(model)
    }

    override fun addData(data: MutableList<CarouselItemModel>) {
        adapter.add(data)
    }

    override fun onLastItemReached() {
        presenter.load(false)
    }

    override fun showProgress() {
        progress.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = INVISIBLE
    }

    override fun showMessage(message: String) {
        alert.visibility = VISIBLE
        carousel.visibility = GONE
        alert.text = message
    }

    override fun hideMessage() {
        carousel.visibility = VISIBLE
        alert.visibility = INVISIBLE
    }
}