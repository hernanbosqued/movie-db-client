package com.hernanbosqued.movie_db_client.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel : ViewModel(), RepositoryCallback<ListModel<VideoResultModel>> {

    @Inject
    lateinit var service: CarouselService

    private val state = BehaviorSubject.create<DetailState>()
    val model = ObservableField<CarouselItemModel>()
    val hasVideo = ObservableBoolean(false)

    init {
        ComponentHolder.component<AppComponent>().inject(this)
    }

    fun start(param: CarouselItemModel) = viewModelScope.launch {
        model.set(param)
        hasVideo.set(param.hasVideo)

        param.path?.let {
            state.onNext(DetailState.Poster(it))
        }

        param.ranking?.let {
            state.onNext(DetailState.Ranking(it))
        }

        if (param.hasVideo) {
            service.videos(param.type!!, param.id, this@DetailViewModel)
        }
    }

    override fun onSuccess(data: ListModel<VideoResultModel>) {
        state.onNext(DetailState.Video(data.results!!.first()))
    }

    override fun onFail(error: ErrorModel) {
        hasVideo.set(false)
        state.onNext(DetailState.Message(error.code.toString() + " - " + error.message))
    }

    fun state(): Observable<DetailState> = state.observeOn(Schedulers.trampoline())
}


