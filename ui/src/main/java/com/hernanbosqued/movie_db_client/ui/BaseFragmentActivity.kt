package com.hernanbosqued.movie_db_client.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragmentActivity : AppCompatActivity() {

    fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right)
        if (addToBackStack) {
            val last = supportFragmentManager.fragments.last()
            ft.hide(last)
            ft.addToBackStack(null)
        }

        ft.add(android.R.id.content, fragment, fragment::class.java.simpleName)
        ft.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}