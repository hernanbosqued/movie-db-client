package com.hernanbosqued.movie_db_client.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import com.hernanbosqued.movie_db_client.ui.postWithDelay
import com.hernanbosqued.movie_db_client.ui.setWithDelay
import javax.inject.Inject

class DetailViewModel : ViewModel(), RepositoryCallback<ListModel<VideoResultModel>> {

    @Inject
    lateinit var service: CarouselService

    private val state = MutableLiveData<DetailState>()

    val showVideo = ObservableBoolean(false)

    var model: CarouselItemModel? = null
        set(model) {
            model?.let {
                model.path?.let {
                    state.postValue(DetailState.Poster(it))
                }

                model.ranking?.let {
                    state.postWithDelay(DetailState.Ranking(it))
                }

                if (model.hasVideo) {
                    service.videos(model.type!!, model.id, this@DetailViewModel)
                }
            }
            field = model
        }

    init {
        ComponentHolder.component<AppComponent>().inject(this)
    }

    override fun onSuccess(data: ListModel<VideoResultModel>) {
        data.results?.let { results ->
            if (results.isNotEmpty()) {
                showVideo.setWithDelay(true)
                state.postWithDelay(DetailState.Video(results.first()))
            }
        }
    }

    override fun onFail(error: ErrorModel) {
        showVideo.setWithDelay(false)
        state.postWithDelay(DetailState.Message(error.code.toString() + " - " + error.message))
    }

    fun state(): LiveData<DetailState> = state
}