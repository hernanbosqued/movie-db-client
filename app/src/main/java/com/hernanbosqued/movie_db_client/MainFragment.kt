package com.hernanbosqued.movie_db_client

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.MoviesClient
import com.hernanbosqued.domain.TVClient
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.BaseFragmentActivity.BackPressedCallbacks
import com.hernanbosqued.repo.RepositoryImpl
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : BaseFragment<MainFragment.Callbacks?>(), BackPressedCallbacks, MainContract.View{
    private lateinit var dialog: Dialog
    private lateinit var presenter: MainPresenter
    private lateinit var emptyView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = MainPresenter()
    }

    override fun addCarousel(client: Client, title: String) {
        val container = view?.findViewById<LinearLayout>(R.id.container)
        container?.addView(CarouselView(context!!, client, title))
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

        prepareDialog()
        prepareEmptyView(view)
    }

    private fun prepareEmptyView(view: View) {
        emptyView = view.findViewById(R.id.empty_view)
    }

    private fun prepareDialog() {
        dialog = Dialog(activity!!, R.style.TransparentDialog)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_dialog)
    }

    fun performSearch(query: String) {
        presenter.processQuery(query)
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

    override fun onBackPressedCallback(): Boolean {
        return false
    }

    interface Callbacks {
        fun fromMainFragment()
    }

    override val dummyCallbacks: Callbacks
        get() = object : Callbacks {
            override fun fromMainFragment() {}
        }
}