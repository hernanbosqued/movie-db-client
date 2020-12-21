package com.hernanbosqued.movie_db_client.ui

import android.content.Context
import androidx.fragment.app.Fragment

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<FRAGMENT_CALLBACK> : Fragment() {

    var callback: FRAGMENT_CALLBACK? = null

    abstract val dummyCallback: FRAGMENT_CALLBACK

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