package com.hernanbosqued.movie_db_client.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {

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
}