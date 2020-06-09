package com.hernanbosqued.movie_db_client

import androidx.fragment.app.Fragment

class DetailActivity : BaseFragmentActivity(), DetailFragment.Callbacks {

    override fun getFragment(): Fragment {
        return DetailFragment()
    }

}