package com.hernanbosqued.movie_db_client.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import com.hernanbosqued.movie_db_client.ui.postWithDelay
import javax.inject.Inject

class DetailViewModel : ViewModel() {

    @Inject
    lateinit var service: CarouselService

    private val state = MutableLiveData<DetailState>()

    var model: CarouselItemModel? = null
        set(model) {
            model?.let {
                model.path?.let { path ->
                    state.postValue(DetailState.Poster(path))
                }

                model.ranking?.let { ranking ->
                    state.postWithDelay(DetailState.Ranking(ranking))
                }

                when (MEDIATYPE.fromValue(it.type!!)) {
                    MEDIATYPE.TV -> {
                        service.tvDetails(model.id, object : RepositoryCallback<TVDetailsModel> {
                            override fun onSuccess(data: TVDetailsModel) {
                                data.seasons?.let { seasons ->
                                    state.postValue(DetailState.Seasons(seasons))
                                }
                            }

                            override fun onFail(error: ErrorModel) {
                                state.postValue(DetailState.Message(error.code.toString() + " - " + error.message))
                            }

                        })
                    }
                    else -> Unit
                }
            }
            field = model
        }

    init {
        ComponentHolder.component<AppComponent>().inject(this)
    }

    fun seasonSelected(seasonNumber: Int) {
        service.tvSeasonDetails(model!!.id, seasonNumber, object : RepositoryCallback<SeasonModel> {
            override fun onSuccess(data: SeasonModel) {
                data.episodes?.let {
                    state.postValue(DetailState.Episodes(it))
                }
            }

            override fun onFail(error: ErrorModel) {
            }
        })
    }

    fun state(): LiveData<DetailState> = state
}