package com.hernanbosqued.movie_db_client.ui

import android.os.Bundle
import androidx.arch.core.executor.DefaultTaskExecutor
import com.hernanbosqued.movie_db_client.domain.model.ResultModel

class ListActivity : BaseFragmentActivity(), ListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        addFragment(ListFragment(), false)
    }

    override fun fromMainFragment(model: ResultModel) {
        supportFragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName)?:run {
            val fragment = DetailFragment()
            fragment.arguments = Bundle().apply {
                putSerializable("model", model)
            }
            addFragment(fragment, true)
        }
    }
}