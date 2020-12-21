package com.hernanbosqued.movie_db_client.ui.carousel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.CarouselClientCallback
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.CarouselService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CarouselViewModel @Inject constructor(private val service: CarouselService, private val resources: ResourcesRepository) :
    ViewModel(), CarouselClientCallback {

    private val state = BehaviorSubject.create<CarouselState>()

    private val carouselModel = ObservableField<CarouselModel>()
    val showInfo = ObservableBoolean(false)
    val showProgress = ObservableBoolean(false)
    val showMessage = ObservableBoolean(false)

    fun loadMore() {
        load(carouselModel.get()!!, false)
    }

    fun load(model: CarouselModel, first: Boolean) {
        carouselModel.set(model)
        showInfo.set(model.info)
        if (first || model.page < model.totalPages) {
            val method = service.javaClass.methods.filterNotNull().find {
                model.method.contentEquals(it.name)
            }
            showProgress.set(true)
            method?.invoke(service, model.page + 1, model.query, this)
        }
    }

    private fun updateModel(param: CarouselModel) {
        carouselModel.get()?.apply {
            title = param.title.format(param.query)
            page = param.page
            totalPages = param.totalPages
            totalResults = param.totalResults
            list.addAll(param.list)
            results = list.size

            state.onNext(CarouselState.Data(param.list))
            state.onNext(CarouselState.Info(title, page, totalPages, totalResults, results))

            checkListContent()
        }
    }

    private fun checkListContent() {
        if (carouselModel.get()?.list.isNullOrEmpty()) {
            showInfo.set(false)
            showMessage.set(true)
            state.onNext(CarouselState.Message(resources.noResultsFound()))
        } else
            showMessage.set(false)
    }

    override fun onOK(model: CarouselModel) {
        showProgress.set(false)
        updateModel(model)
    }

    override fun onError(error: String) {
        showProgress.set(false)
        showInfo.set(false)
        showMessage.set(true)
        state.onNext(CarouselState.Message(error))
        checkListContent()
    }

    fun state(): Observable<CarouselState> = state.observeOn(Schedulers.trampoline())
}