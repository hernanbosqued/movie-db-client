package com.hernanbosqued.movie_db_client.ui

import android.os.Bundle
import android.view.View
import android.view.View.MeasureSpec
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.MOVIE
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.TV
import com.hernanbosqued.movie_db_client.domain.orElse
import com.hernanbosqued.movie_db_client.domain.then
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment<ListFragment.Callbacks>(), SearchView.OnQueryTextListener,
    android.widget.SearchView.OnQueryTextListener, com.hernanbosqued.movie_db_client.presenter.ListContract.View, CarouselListeners {
    private lateinit var presenter: com.hernanbosqued.movie_db_client.presenter.ListPresenter

    interface Callbacks {
        fun fromMainFragment(model: CarouselItemModel)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = com.hernanbosqued.movie_db_client.presenter.ListPresenter(
            view = this,
            repository = RepositoryImpl(context!!)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareSearchView()

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

    override fun addCarousel(model: CarouselModel, onTop: Boolean) {
        val carouselView = CarouselView(context!!, this)
        val params = carouselView.layoutParams as ConstraintLayout.LayoutParams
        params.setMargins(20)
        carouselView.bind(model)

        val index: Int = (onTop then 0 orElse -1)!!
        container.addView(carouselView, index)

        carouselView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )

        carouselView.translationY = (-carouselView.measuredHeight).toFloat()
        carouselView.animate().translationY(0F)
    }

    override fun onItemClicked(model: CarouselItemModel) {
        callbacks?.fromMainFragment(model)
    }

    override fun onCarouselClicked(model: CarouselModel) {
        //delete a list?
        showMessage(model.title)
    }

    override fun initialSelection(moviesChecked: Boolean, tvChecked: Boolean) {
        checkbox_movies.isChecked = moviesChecked
        checkbox_tv.isChecked = moviesChecked

        checkbox_movies.setOnCheckedChangeListener { _, _ ->
            presenter.checkboxChanged(MOVIE)
        }
        checkbox_tv.setOnCheckedChangeListener { _, _ ->
            presenter.checkboxChanged(TV)
        }
    }

    override fun scrollTop() {
        scroll_view.smoothScrollTo(0, 0)
    }

    private fun prepareSearchView() {
        search_view.isIconified = true
        search_view.clearFocus()
        search_view.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search_view.clearFocus()
        presenter.processQuery(query)
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


