package com.hernanbosqued.movie_db_client.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE
import com.hernanbosqued.movie_db_client.ui.BaseFragment
import com.hernanbosqued.movie_db_client.ui.R
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselFragment
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselListeners
import com.hernanbosqued.movie_db_client.ui.databinding.LayoutListBinding
import io.reactivex.disposables.CompositeDisposable

class ListFragment : BaseFragment<ListFragment.Callback>(), SearchView.OnQueryTextListener, CarouselListeners {

    private val viewModel: ListViewModel by viewModels()

    private val compositeDisposable = CompositeDisposable()

    private lateinit var binding: LayoutListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LayoutListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.layout_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSearchView()
        registerObservers()
        viewModel.start()
    }

    private fun registerObservers() {
        compositeDisposable.add(viewModel.state().subscribe(this::handleState))
    }

    private fun handleState(state: ListState) {
        when (state) {
            is ListState.Search -> changeSearch(state.movies, state.tv)
            is ListState.Carousel -> addCarousel(state.model, state.onTop)
            is ListState.Message -> showMessage(state.message)
        }
    }

    private fun changeSearch(moviesChecked: Boolean, tvChecked: Boolean) {
        binding.checkboxMovies.isChecked = moviesChecked
        binding.checkboxTv.isChecked = tvChecked

        binding.checkboxMovies.setOnCheckedChangeListener { _, _ -> viewModel.checkboxChanged(MEDIATYPE.MOVIE) }
        binding.checkboxTv.setOnCheckedChangeListener { _, _ -> viewModel.checkboxChanged(MEDIATYPE.TV) }
    }

    private fun addCarousel(model: CarouselModel, onTop: Boolean) {
        val fragmentContainer = FrameLayout(requireContext())
        fragmentContainer.id = View.generateViewId()

        val params = FrameLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(20, 20, 20, 20)
        fragmentContainer.layoutParams = params

        val transaction = childFragmentManager.beginTransaction()
        transaction.add(fragmentContainer.id, CarouselFragment(model, this), "carouselFragment${fragmentContainer.id}")
        transaction.commit()

        val index = (if (onTop) 0 else -1)
        binding.container.addView(fragmentContainer, index)

        if (onTop) {
            scrollTop()
        }
    }

    override fun onItemClicked(view: View, model: CarouselItemModel) {
        callback?.fromFragment(view, model)
    }

    override fun onCarouselClicked(model: CarouselModel) {
        //delete a list?
        showMessage(model.title)
    }

    private fun scrollTop() {
        binding.scrollView.smoothScrollTo(0, 0)
    }

    private fun prepareSearchView() {
        binding.searchView.isIconified = true
        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        binding.searchView.clearFocus()
        viewModel.processQuery(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //do nothing
        return false
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }

    interface Callback {
        fun fromFragment(view: View, model: CarouselItemModel)
    }

    override val dummyCallback: Callback
        get() = object : Callback {
            override fun fromFragment(view: View, model: CarouselItemModel) {}
        }
}