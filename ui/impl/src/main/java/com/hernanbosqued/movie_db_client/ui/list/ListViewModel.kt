package com.hernanbosqued.movie_db_client.ui.list

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.MOVIE
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.TV
import com.hernanbosqued.movie_db_client.ui.detail.DetailState
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: Repository, val resources: ResourcesRepository) :
    ViewModel(), RepositoryCallback<CarouselListModel> {

    private val state = BehaviorSubject.create<ListState>()
    val model = ObservableField<ArrayList<CarouselModel>>()

    private var searchSelection = setOf(TV, MOVIE)

    fun start() = viewModelScope.launch {
        repository.carouselList(this@ListViewModel)
        state.onNext(ListState.Search(searchSelection[MOVIE].isChecked(), searchSelection[TV].isChecked()))
    }

    fun processQuery(query: String) {
        if (searchSelection.any { it.isChecked() }) {
            val carousel = CarouselModel().apply {
                this.query = query
                this.method = searchSelection.getTypes()
            }
            state.onNext(ListState.Carousel(carousel, true))
        } else {
            state.onNext(ListState.Message(resources.nothingSelected()))
        }
    }

    fun checkboxChanged(type: MEDIATYPE) {
        searchSelection[type].change()
    }

    override fun onSuccess(data: CarouselListModel) {
        model.set(data.results as ArrayList<CarouselModel>)
        data.results.forEach {
            state.onNext(ListState.Carousel(it, false))
        }
    }

    override fun onFail(error: ErrorModel) {
        state.onNext(ListState.Message(error.message))
    }

    fun state(): Observable<ListState> = state.observeOn(Schedulers.trampoline())
}


