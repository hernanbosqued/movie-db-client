package com.hernanbosqued.movie_db_client

import android.os.Bundle
import com.hernanbosqued.domain.model.ResultModel

class ListActivity : BaseFragmentActivity(), ListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        addFragment(ListFragment(), false)
    }

    override fun fromMainFragment(model: ResultModel) {
        val fragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("model", model)
            }
        }
        addFragment(fragment, true)
    }
}