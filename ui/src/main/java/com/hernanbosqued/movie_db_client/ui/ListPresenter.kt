package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.MOVIE
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.TV
import javax.inject.Inject

class ListPresenter(view: ListContract.View) :
    BasePresenter<ArrayList<CarouselModel>, ListContract.View>(ArrayList(), view),
    ListContract.Presenter, RepositoryCallback<CarouselListModel> {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var resources: ResourcesRepository

    private var searchSelection = setOf(TV, MOVIE)

    init {
        MyApp.component.poke(this)

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


