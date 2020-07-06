package com.hernanbosqued.movie_db_client.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        val fragment = DetailFragment()
        fragment.arguments = Bundle().apply {
            putSerializable("model", intent.getSerializableExtra("model"))
        }

        supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, fragment::class.java.simpleName)
            .commit()
    }
}