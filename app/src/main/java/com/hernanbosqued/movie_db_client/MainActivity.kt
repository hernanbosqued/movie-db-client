package com.hernanbosqued.movie_db_client

import android.content.Intent
import androidx.fragment.app.Fragment
import com.hernanbosqued.domain.model.ResultModel

class MainActivity : BaseFragmentActivity(), MainFragment.Callbacks {

    override fun getFragment(): Fragment {
        return MainFragment()
    }

    override fun fromMainFragment(model: ResultModel) {
        startActivity(Intent(this,DetailActivity::class.java))
    }
}