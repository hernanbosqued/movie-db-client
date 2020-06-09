package com.hernanbosqued.movie_db_client

import android.os.Bundle

class DetailFragment : BaseFragment<DetailFragment.Callbacks>() {
    interface Callbacks

    companion object {
        const val TAG: String = "DETAIL"
    }

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = DetailPresenter(savedInstanceState == null)
    }

    override val dummyCallbacks: Callbacks
        get() = object : Callbacks {
        }
}
