package com.hernanbosqued.movie_db_client.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment<ListFragment.Callbacks>(), SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener, ListContract.View, CarouselListeners {
    private lateinit var presenter: ListPresenter

    interface Callbacks {
        fun fromMainFragment(model: CarouselItemModel)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ListPresenter(view = this, repository = RepositoryImpl(context!!))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareSearchView()
        prepareCheckboxes()

        if (savedInstanceState == null) {
            presenter.bind()
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

    override fun addCarousel(model: CarouselModel) {
        val view = CarouselView(context!!, this)
        view.bind(model)

        container.addView(view, 0)

        lifecycle.addObserver(view)

        //scrollTop()
    }

    override fun onItemClicked(model: CarouselItemModel) {
        callbacks?.fromMainFragment(model)
    }

    override fun onCarouselClicked(model: CarouselModel) {
        //delete a list?
        showMessage(model.title)
    }

    private fun prepareCheckboxes() {
        checkbox_movies.setOnCheckedChangeListener { _, _ ->
            if (!checkbox_tv.isChecked) {
                checkbox_movies.isChecked = true
            }
        }
        checkbox_tv.setOnCheckedChangeListener { _, _ ->
            if (!checkbox_movies.isChecked) {
                checkbox_tv.isChecked = true
            }
        }

        checkbox_movies.isChecked = true
    }

    override fun scrollTop() {
        //scroll_view.smoothScrollTo(0, 0)
    }

    private fun prepareSearchView() {
        search_view.isIconified = true
        search_view.clearFocus()
        search_view.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search_view.clearFocus()
        presenter.processQuery(query, checkbox_movies.isChecked, checkbox_tv.isChecked)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //do nothing
        return false
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override val dummyCallbacks: Callbacks
        get() = object : Callbacks {
            override fun fromMainFragment(model: CarouselItemModel) {}
        }
}