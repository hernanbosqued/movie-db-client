package com.hernanbosqued.movie_db_client

import android.content.Intent
import androidx.fragment.app.Fragment
import com.hernanbosqued.domain.model.ResultModel

class ListActivity : BaseFragmentActivity(), ListFragment.Callbacks {

    override fun getFragment(): Fragment {
        return ListFragment()
    }

    override fun fromMainFragment(model: ResultModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", model.id)
        startActivity(intent)
    }
}