package com.hernanbosqued.movie_db_client

import android.content.Context
import androidx.fragment.app.Fragment

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<T> : Fragment() {
    var callbacks: T? = null
    abstract val dummyCallbacks: T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callbacks = context as T
        } catch (e: Exception) {
            throw ClassCastException("Activity must implement fragment's callbacks.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = dummyCallbacks
    }
}