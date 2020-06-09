package com.hernanbosqued.movie_db_client

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.adapter.CarouselsAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainFragment.Callbacks?>(), SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener, MainContract.View, CarouselListeners {
    private val adapter: CarouselsAdapter = CarouselsAdapter(this)
    private val presenter: MainPresenter = MainPresenter(this)

    private lateinit var dialog: Dialog
    private lateinit var emptyView: View

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
        query = savedInstanceState?.getString("query")
        prepareDialog()
        prepareEmptyView(view)
        prepareSearchView()
        prepareCheckboxes()
        prepareRecyclerView()

        if (savedInstanceState == null) {
            presenter.addCarousels()
        }
    }

    private fun prepareRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        container.layoutManager = linearLayoutManager
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        container.adapter = adapter
    }

    override fun addCarousel(model: CarouselModel) {
        adapter.add(model)
    }

    override fun onItemClicked(model: ResultModel) {
        callbacks?.fromMainFragment(model)
    }

    override fun onCarouselClicked(model: ListModel) {
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
        container.smoothScrollToPosition(0)
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
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //do nothing
        return false
    }

    private fun prepareEmptyView(view: View) {
        emptyView = view.findViewById(R.id.empty_view)
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