package com.hernanbosqued.movie_db_client.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import kotlinx.android.synthetic.main.layout_item.poster

class ListActivity : BaseFragmentActivity(), ListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        addFragment(ListFragment(), false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun fromMainFragment(model: CarouselItemModel) {
        val intent = Intent(this, DetailActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this, poster, "poster")
        intent.putExtra("model", model)
        startActivity(intent, options.toBundle())

/*        supportFragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName) ?: run {
            val fragment = DetailFragment()
            fragment.arguments = Bundle().apply {
                putSerializable("model", model)
            }
            addFragment(fragment, true)
        }*/
    }
}