package com.hernanbosqued.movie_db_client.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.MOVIE
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.TV
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import com.hernanbosqued.movie_db_client.ui.postWithDelay
import javax.inject.Inject

class ListViewModel : ViewModel(), RepositoryCallback<CarouselListModel> {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var resources: ResourcesRepository

    private val state = MutableLiveData<ListState>()

    private var searchSelection = setOf(TV, MOVIE)

    init {
        ComponentHolder.component<AppComponent>().inject(this)
    }

    fun start() {
        repository.carouselList(this@ListViewModel)
        state.value = ListState.Search(searchSelection[MOVIE].isChecked(), searchSelection[TV].isChecked())
    }

    fun processQuery(query: String) {
        if (searchSelection.any { it.isChecked() }) {
            val carousel = CarouselModel().apply {
                this.query = query
                this.method = searchSelection.getTypes()
            }
            state.value = ListState.Carousel(carousel, true)
        } else {
            state.value = ListState.Message(resources.nothingSelected())
        }
    }

    fun checkboxChanged(type: MEDIATYPE) {
        searchSelection[type].change()
    }

    override fun onSuccess(data: CarouselListModel) {
        data.results.forEach {
            state.postWithDelay(ListState.Carousel(it, false))
        }
    }

    override fun onFail(error: ErrorModel) {
        state.value = ListState.Message(error.message)
    }

    fun state(): LiveData<ListState> = state
}
