package com.hernanbosqued.movie_db_client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    //abstract fun getFragment(): Fragment

//    private fun findFragment(): Fragment {
//        return supportFragmentManager.findFragmentByTag(TAG) ?: getFragment()
//    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right)
        if (addToBackStack) {
            val last = supportFragmentManager.fragments.last()
            ft.hide(last)
            ft.add(R.id.fragmentContainer, fragment)
            ft.addToBackStack(null)
        } else {
            ft.add(R.id.fragmentContainer, fragment)
        }

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