package com.hernanbosqued.movie_db_client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragmentActivity<F:Fragment> : AppCompatActivity() {
    companion object{
        private const val FRAGMENT_TAG = "tag"
        private const val STACK_KEY = "stack"
    }

    protected abstract val fragment: F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        savedInstanceState?:run{
            initFragment()
        }
    }

    private fun initFragment() {
        replaceContent(fragment, false, FRAGMENT_TAG)
    }

    override fun onBackPressed() {
        if (currentFragment is BackPressedCallbacks) {
            if ((fragment as BackPressedCallbacks).onBackPressedCallback()) {
                return
            }
        }
        super.onBackPressed()
    }

    val currentFragment: F?
        get() {
            return try {
                supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as F?
            } catch (e: ClassCastException) {
                null
            }
        }

    private fun replaceContent(fragment: F, addToBackStack: Boolean, tag: String) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainer, fragment, tag)
        if (addToBackStack) {
            ft.addToBackStack(STACK_KEY)
        }
        ft.commit()
    }

    interface BackPressedCallbacks {
        fun onBackPressedCallback(): Boolean
    }
}