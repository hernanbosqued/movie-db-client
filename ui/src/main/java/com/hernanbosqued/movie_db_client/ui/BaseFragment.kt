package com.hernanbosqued.movie_db_client.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<FRAGMENT_CALLBACKS> : Fragment() {

    abstract fun getLayout(): Int

    var callbacks: FRAGMENT_CALLBACKS? = null

    abstract val dummyCallbacks: FRAGMENT_CALLBACKS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onAttach(context: Context) {
        try {
            callbacks = context as FRAGMENT_CALLBACKS
        } catch (e: Exception) {
            throw ClassCastException("Activity must implement fragment's callbacks.")
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        callbacks = dummyCallbacks
        super.onDetach()
    }
}