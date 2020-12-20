package com.hernanbosqued.movie_db_client.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val map: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {

        val creator = map[viewModelClass] ?: map.asIterable().firstOrNull {
            viewModelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $viewModelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}