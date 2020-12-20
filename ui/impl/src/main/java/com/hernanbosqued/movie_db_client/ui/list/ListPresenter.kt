package com.hernanbosqued.movie_db_client.ui.list

import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.MOVIE
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.TV
import com.hernanbosqued.movie_db_client.ui.BasePresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(view: ListContract.View, private val repository: Repository, val resources: ResourcesRepository) :
    BasePresenter<ArrayList<CarouselModel>, ListContract.View>(ArrayList(), view), ListContract.Presenter, RepositoryCallback<CarouselListModel> {

    private var searchSelection = setOf(TV, MOVIE)

    override fun start() {
        repository.carouselList(this)

        view().initialSelection(
            searchSelection[MOVIE].isChecked(),
            searchSelection[TV].isChecked()
        )
    }

    override fun processQuery(query: String) {
        if (searchSelection.any { it.isChecked() }) {
            val carousel = CarouselModel().apply {
                this.query = query
                this.method = searchSelection.getTypes()
            }
            view().addCarousel(carousel, true)
        } else {
            view().showMessage(resources.nothingSelected())
        }
    }

    override fun checkboxChanged(type: MEDIATYPE) {
        searchSelection[type].change()
    }

    override fun onSuccess(data: CarouselListModel) {
        model().addAll(data.results)
        data.results.forEach { view().addCarousel(it, false) }
    }

    override fun onFail(error: ErrorModel) {
        view().showMessage(error.message)
    }
}

