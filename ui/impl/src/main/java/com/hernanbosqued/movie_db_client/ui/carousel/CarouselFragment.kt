package com.hernanbosqued.movie_db_client.ui.carousel

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.ui.*
import com.hernanbosqued.movie_db_client.ui.databinding.LayoutCarrouselBinding
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@SuppressLint("ViewConstructor")
class CarouselFragment(val model: CarouselModel, private val listener: CarouselListeners) :
    BaseFragment<CarouselFragment.Callbacks>(), ScrollListener, LifecycleObserver {

    @Inject
    lateinit var viewModel: CarouselViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable = CompositeDisposable()

    private val binding: LayoutCarrouselBinding by lazy {
        DataBindingUtil.setContentView(this.requireActivity(), getLayout())
    }

    override fun getLayout(): Int {
        return R.layout.layout_carrousel
    }

    private val adapter = ItemsAdapter(this, listener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()

        this.view?.background = ContextCompat.getDrawable(requireContext(), R.drawable.placeholder)
        this.view?.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.placeholder_light))

        viewModel = ViewModelProvider(this, viewModelFactory).get(CarouselViewModel::class.java)
        registerObservers()

        binding.viewModel = viewModel

        viewModel.load(model, true)

        this.view?.setOnClickListener {
            listener.onCarouselClicked(model)
        }
    }

    private fun prepareRecyclerView() {
        binding.carousel.addItemDecoration(SpacingItemDecoration(resources.getDimension(R.dimen.spacing_size).toInt()))
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.isItemPrefetchEnabled = true
        layoutManager.recycleChildrenOnDetach = true
        binding.carousel.layoutManager = layoutManager
        binding.carousel.adapter = adapter
    }

    private fun registerObservers() {
        compositeDisposable.add(viewModel.state().subscribe(this::handleState))
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
        binding.pageText.text = Utils.getSpan(getString(R.string.page), page.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
        binding.resultsText.text = Utils.getSpan(getString(R.string.results), results.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
        binding.totalPagesText.text = Utils.getSpan(getString(R.string.total_pages), totalPages.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
        binding.totalResultsText.text = Utils.getSpan(getString(R.string.total_results), totalResults.toString(), ContextCompat.getColor(requireContext(), R.color.colorAccent))
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

    interface Callbacks

    override val dummyCallback: Callbacks
        get() = object : Callbacks {
        }
}