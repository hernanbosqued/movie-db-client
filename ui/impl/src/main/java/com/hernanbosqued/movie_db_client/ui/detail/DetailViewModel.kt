package com.hernanbosqued.movie_db_client.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class DetailViewModel : ViewModel(), RepositoryCallback<ListModel<VideoResultModel>> {

    @Inject
    lateinit var service: CarouselService

    private val state = BehaviorSubject.create<DetailState>()
    val model = ObservableField<CarouselItemModel>()
    val showVideo = ObservableBoolean(false)
    val showAlert = ObservableBoolean(false)

    init {
        ComponentHolder.component<AppComponent>().inject(this)
    }

    fun setModel(param: CarouselItemModel) {
        model.set(param)
    }

    fun start() {
        model.get()?.let { model ->
            model.path?.let {
                state.onNext(DetailState.Poster(it))
            }

            model.ranking?.let {
                state.onNext(DetailState.Ranking(it))
            }
            if (model.hasVideo) {
                service.videos(model.type!!, model.id, this@DetailViewModel)
            }
        }
    }

    override fun onSuccess(data: ListModel<VideoResultModel>) {
        data.results?.let { results ->
            if (results.isNotEmpty()) {
                state.onNext(DetailState.Video(results.first()))
                showVideo.set(true)
            } else {
                showAlert.set(true)
            }
        }
    }

    override fun onFail(error: ErrorModel) {
        showAlert.set(true)
        state.onNext(DetailState.Message(error.code.toString() + " - " + error.message))
    }

    fun state(): Observable<DetailState> = state.observeOn(Schedulers.trampoline())
}


