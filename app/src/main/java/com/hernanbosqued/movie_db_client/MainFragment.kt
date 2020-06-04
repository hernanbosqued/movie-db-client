package com.hernanbosqued.movie_db_client

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.BaseFragmentActivity.BackPressedCallbacks

class MainFragment : BaseFragment<MainFragment.Callbacks?>(), BackPressedCallbacks, MainContract.View, BaseAdapterListener {
    private lateinit var dialog: Dialog
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: ItemsAdapter
    private lateinit var emptyView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = MainPresenter()
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView(view)
        prepareDialog()
        prepareEmptyView(view)
    }

    private fun prepareEmptyView(view: View) {
        emptyView = view.findViewById(R.id.empty_view)
    }

    private fun prepareRecyclerView(view: View) {
        recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context!!))
        adapter = ItemsAdapter()
        adapter.listener = this
        recyclerView.adapter = adapter
    }

    private fun prepareDialog() {
        dialog = Dialog(activity!!, R.style.TransparentDialog)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_dialog)
    }

    fun performSearch(query: String) {
        presenter.processQuery(query)
    }

    override fun showItems(model: List<ResultModel>) {
        adapter.setData(model)
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

    override fun showEmpty() {
        emptyView.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        emptyView.visibility = View.GONE
    }

    override fun scrollToTop() {
        recyclerView.smoothScrollToPosition(0)
    }

    override fun onBackPressedCallback(): Boolean {
        return false
    }

    override fun onBottom() {
        presenter.loadMore()
    }

    interface Callbacks {
        fun fromMainFragment()
    }

    override val dummyCallbacks: Callbacks
        get() = object : Callbacks {
            override fun fromMainFragment() {}
        }
}