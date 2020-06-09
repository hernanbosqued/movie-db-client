package com.hernanbosqued.movie_db_client

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainFragment.Callbacks?>(), SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener, MainContract.View, CarouselListeners {
    private val presenter: MainPresenter = MainPresenter(this)

    private lateinit var dialog: Dialog

    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbindView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareDialog()
        prepareSearchView()
        prepareCheckboxes()

        if (savedInstanceState == null) {
            presenter.addCarousels()
        }
    }

    override fun addCarousel(model: CarouselModel) {
        val view= CarouselView(context!!, this)
        view.bind(model)
        container.addView(view,0)
        scrollTop()
    }

    override fun addCarousel(model: List<CarouselModel>) {
        for (item in model) {
            addCarousel(item)
        }
    }

    override fun onItemClicked(model: ResultModel) {
        callbacks?.fromMainFragment(model)
    }

    override fun onCarouselClicked(model: ListModel) {
        //delete a list?
        model.title?.let { showMessage(it) }
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
        scroll_view.smoothScrollTo(0,0)
    }

    private fun prepareSearchView() {
        if (!query.isNullOrEmpty()) {
            search_view.isIconified = true
            search_view.setQuery(query, false)
            search_view.clearFocus()
        }

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

    private fun prepareDialog() {
        dialog = Dialog(activity!!, R.style.TransparentDialog)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_dialog)
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        dialog.show()
    }

    override fun hideProgress() {
        dialog.dismiss()
    }

    interface Callbacks {
        fun fromMainFragment(model: ResultModel)
    }

    override val dummyCallbacks: Callbacks
        get() = object : Callbacks {
            override fun fromMainFragment(model: ResultModel) {}
        }
}