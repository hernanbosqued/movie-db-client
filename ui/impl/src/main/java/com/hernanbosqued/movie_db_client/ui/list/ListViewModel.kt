package com.hernanbosqued.movie_db_client.ui.list

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.MOVIE
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.TV
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ListViewModel : ViewModel(), RepositoryCallback<CarouselListModel> {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var resources: ResourcesRepository

    private val state = BehaviorSubject.create<ListState>()
    val model = ObservableField<ArrayList<CarouselModel>>()

    private var searchSelection = setOf(TV, MOVIE)

    init {
        ComponentHolder.component<AppComponent>().inject(this)
    }

    fun start() {
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


