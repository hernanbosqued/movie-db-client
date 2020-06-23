package com.hernanbosqued.movie_db_client.ui
import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.MOVIE
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE.TV

class ListPresenter(view: ListContract.View, var repository: Repository) : BasePresenter<List<CarouselModel>, ListContract.View>(view), ListContract.Presenter, RepositoryCallbacks<CarouselListModel> {

    private var searchSelection = setOf<MEDIATYPE>(TV, MOVIE)

    override fun processQuery(query: String) {
        val carousel = CarouselModel().apply {
            this.query = query
            this.method = searchSelection.getTypes()
        }
        view()?.addCarousel(carousel,true)
    }


    override fun bind() {
        repository.carouselList(this)

        view()?.initialSelection(searchSelection[MOVIE].isChecked(), searchSelection[TV].isChecked())
    }

    override fun checkboxChanged(type: MEDIATYPE) {
         searchSelection[type].change()
    }

    override fun onSuccess(data: CarouselListModel) {
        data.results.forEach { view()?.addCarousel(it,false) }
    }

    override fun onFail(error: ErrorModel) {
        view()?.showMessage(error.message)
    }
}


