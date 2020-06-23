package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class MovieResultModel : ResultModel(), Serializable, Visitable {

    override fun visit(visitor: Visitor) {
        super.visit(visitor)
        visitor.accept(this)
    }

    override fun parse( ): CarouselItemModel {
        return CarouselItemModel().apply {
            this.id = this@MovieResultModel.id
            this.hasVideo = this@MovieResultModel.hasVideo
            this.overview = this@MovieResultModel.overview
            this.title = this@MovieResultModel.title
            this.path = this@MovieResultModel.posterPath
            this.ranking = this@MovieResultModel.ranking
            this.type = this@MovieResultModel.getType()
        }
    }

    @SerializedName(value = "id")
    val id: Int = - 1

    @SerializedName(value = "title")
    val title: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null
}
