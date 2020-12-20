package com.hernanbosqued.movie_db_client.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.ui.CarouselService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val service: CarouselService) : ViewModel(), RepositoryCallback<ListModel<VideoResultModel>> {

    private val state = BehaviorSubject.create<DetailState>()
    val model = ObservableField<CarouselItemModel>()
    val hasVideo = ObservableBoolean(false)

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


