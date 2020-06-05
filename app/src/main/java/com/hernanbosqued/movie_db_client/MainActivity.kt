package com.hernanbosqued.movie_db_client

import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_base.*


class MainActivity : BaseFragmentActivity<MainFragment>(), SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
    private var query: String? = null
    private val key = "query"

    override val fragment: MainFragment
        get() = MainFragment()


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(key, search_view.query.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        query = savedInstanceState.getString(key)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareSearchView()
        prepareCheckboxes()
    }

    private fun prepareCheckboxes() {
        checkbox_movies.setOnCheckedChangeListener { _, _ ->
            if (!checkbox_tv.isChecked) {
                checkbox_movies.isChecked = true
            }
        }
        checkbox_tv.setOnCheckedChangeListener { _, _ ->
            if (!checkbox_movies.isChecked) {
                checkbox_tv.isChecked = true
            }
        }

        checkbox_movies.isChecked = true
    }

    private fun prepareSearchView() {
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        if (!query.isNullOrEmpty()) {
            search_view.isIconified = true
            search_view.setQuery(query, false)
            search_view.clearFocus()
        }

        search_view.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search_view.clearFocus()
        currentFragment?.performSearch(query,checkbox_movies.isChecked, checkbox_tv.isChecked)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //do nothing
        return false
    }
}