package com.hernanbosqued.movie_db_client.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.ui.CarouselService
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val service: CarouselService) : ViewModel()/* RepositoryCallback<ListModel<VideoResultModel>> */ {

    val model = ObservableField<CarouselItemModel>()

//    val poster = ObservableField("")
//    val overview = ObservableField("")
//    val ranking = ObservableField("")
//    val hasvideo = ObservableBoolean(false)

    fun start(model: CarouselItemModel) {
        this.model.set(model)
    }
//        view().setTitle(model().title!!)
//        view().setPoster(model().path)
//        view().setOverview(model().overview!!)
//        view().setRanking(model().ranking!!)
//
//        if (model().hasVideo) {
//            view().hideEmpty()
//            view().showVideo()
//            service.videos(model().type!!, model().id, this)
//        } else {
//            view().showEmpty()
//        }
}


//    override fun onSuccess(data: ListModel<VideoResultModel>) {
//        view().setVideo(data.results!!.first())
//    }
//
//    override fun onFail(error: ErrorModel) {
//        view().showMessage(error.code.toString() + " - " + error.message)
//    }