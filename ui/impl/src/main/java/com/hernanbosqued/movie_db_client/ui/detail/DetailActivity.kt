package com.hernanbosqued.movie_db_client.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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