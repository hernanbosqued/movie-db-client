package com.hernanbosqued.movie_db_client.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<CALLBACKS> : Fragment() {
    var callbacks: CALLBACKS? = null
    abstract val dummyCallbacks: CALLBACKS

    override fun onAttach(context: Context) {
        Log.i(javaClass.simpleName,"onAttach")
        try {
            callbacks = context as CALLBACKS
        } catch (e: Exception) {
            throw ClassCastException("Activity must implement fragment's callbacks.")
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(javaClass.simpleName,"onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(javaClass.simpleName,"onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(javaClass.simpleName,"onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(javaClass.simpleName,"onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.i(javaClass.simpleName,"onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(javaClass.simpleName,"onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(javaClass.simpleName,"onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(javaClass.simpleName,"onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i(javaClass.simpleName,"onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i(javaClass.simpleName,"onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i(javaClass.simpleName,"onDetach")
        callbacks = dummyCallbacks
        super.onDetach()
    }
}