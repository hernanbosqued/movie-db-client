package com.hernanbosqued.movie_db_client

import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView

class MainActivity : BaseFragmentActivity<MainFragment>(), SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private var query: String? = null
    private val KEY = "query"

    override val fragment: MainFragment
        get() = MainFragment()

    override val actionBarTitle: String
        get() = getString(R.string.app_name)

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY, searchView.query.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        query = savedInstanceState.getString(KEY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        searchView = menu.getItem(0).actionView as SearchView
        prepareSearchView()
        return true
    }

    private fun prepareSearchView() {
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        if (!query.isNullOrEmpty()) {
            searchView.isIconified = false
            searchView.setQuery(query, false)
            searchView.clearFocus()
        }
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView.clearFocus()
        currentFragment?.performSearch(query!!)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //do nothing
        return false
    }
}