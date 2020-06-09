package com.hernanbosqued.movie_db_client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN

abstract class BaseFragmentActivity : AppCompatActivity() {

    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        addFragment(findFragment())
    }

    abstract fun getFragment(): Fragment

    private fun findFragment(): Fragment {
        return supportFragmentManager.findFragmentByTag(TAG) ?: getFragment()
    }

    private fun addFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainer, fragment, TAG)
        ft.commit()
    }
}