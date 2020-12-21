package com.hernanbosqued.movie_db_client.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE
import com.hernanbosqued.movie_db_client.ui.BaseFragment
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselListeners
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselView
import com.hernanbosqued.movie_db_client.ui.databinding.LayoutListBinding
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListFragment : BaseFragment<ListFragment.Callback>(), SearchView.OnQueryTextListener, CarouselListeners {

    private val viewModel: ListViewModel by viewModels()

    private val compositeDisposable = CompositeDisposable()

    private val binding: LayoutListBinding by lazy {
        LayoutListBinding.inflate(LayoutInflater.from(context), null, true)
    }

    init {
        ComponentHolder.component<AppComponent>().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
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
        val view = CarouselView(requireContext(), model, this)
        val params = ConstraintLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(20, 20, 20, 20)
        view.layoutParams = params

        val index = (if (onTop) 0 else -1)
        binding.container.addView(view, index)

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