package com.hernanbosqued.movie_db_client.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<FRAGMENT_CALLBACK> : DaggerFragment() {

    abstract fun getLayout(): Int

    var callback: FRAGMENT_CALLBACK? = null

    abstract val dummyCallback: FRAGMENT_CALLBACK

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onAttach(context: Context) {
        try {
            callback = context as FRAGMENT_CALLBACK
        } catch (e: Exception) {
            throw ClassCastException("Activity must implement fragment's callbacks.")
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        callback = dummyCallback
        super.onDetach()
    }
}