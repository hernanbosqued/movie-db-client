package com.hernanbosqued.movie_db_client

import android.content.Context
import androidx.fragment.app.Fragment


@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<CALLBACKS> : Fragment() {
    var callbacks: CALLBACKS? = null
    abstract val dummyCallbacks: CALLBACKS

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callbacks = context as CALLBACKS
        } catch (e: Exception) {
            throw ClassCastException("Activity must implement fragment's callbacks.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = dummyCallbacks
    }
}