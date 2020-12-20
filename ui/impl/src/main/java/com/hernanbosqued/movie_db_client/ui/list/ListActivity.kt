package com.hernanbosqued.movie_db_client.ui.list

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.layout_item.view.*

class ListActivity : AppCompatActivity(), ListFragment.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, ListFragment(), ListFragment::class.java.simpleName)
            .commit()
    }

    override fun fromFragment(view: View, model: CarouselItemModel) {
        val intent = Intent(this, DetailActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this, view.poster, "poster")
        intent.putExtra("model", model)
        startActivity(intent, options.toBundle())
    }
}