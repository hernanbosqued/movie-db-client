package com.hernanbosqued.movie_db_client.ui.fragment

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
import com.hernanbosqued.movie_db_client.ui.CarouselListeners
import com.hernanbosqued.movie_db_client.ui.CarouselView
import com.hernanbosqued.movie_db_client.ui.R
import com.hernanbosqued.movie_db_client.ui.contract.ListContract
import com.hernanbosqued.movie_db_client.ui.presenter.ListPresenter
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : BaseFragment<ListFragment.Callback>(), SearchView.OnQueryTextListener, ListContract.View, CarouselListeners {

    @Inject
    lateinit var presenter: ListPresenter

    interface Callback {
        fun fromFragment(view: View, model: CarouselItemModel)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSearchView()
        presenter.start()
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
        val carouselView = CarouselView(requireContext(), model, this)
        val params = carouselView.layoutParams as ConstraintLayout.LayoutParams
        params.setMargins(20)

        val index: Int = (onTop then 0 orElse -1)!!
        container.addView(carouselView, index)

        carouselView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )

        carouselView.translationY = (-carouselView.measuredHeight).toFloat()
        carouselView.animate().translationY(0F)

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

    override fun initialSelection(moviesChecked: Boolean, tvChecked: Boolean) {
        checkbox_movies.isChecked = moviesChecked
        checkbox_tv.isChecked = tvChecked

        checkbox_movies.setOnCheckedChangeListener { _, _ -> presenter.checkboxChanged(MOVIE) }
        checkbox_tv.setOnCheckedChangeListener { _, _ -> presenter.checkboxChanged(TV) }
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

    override val dummyCallback: Callback
        get() = object : Callback {
            override fun fromFragment(view: View, model: CarouselItemModel) {}
        }
}


