package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TVResultModel : ResultModel(), Serializable, Visitable {

    override fun visit(visitor: Visitor) {
        super.visit(visitor)
        visitor.accept(this)
    }

    override fun parse( ): CarouselItemModel {
        return CarouselItemModel().apply {
            this.id = this@TVResultModel.id
            this.hasVideo = this@TVResultModel.hasVideo
            this.overview = this@TVResultModel.overview
            this.title = this@TVResultModel.name
            this.path = this@TVResultModel.posterPath
            this.ranking = this@TVResultModel.ranking
            this.type = this@TVResultModel.getType()
        }
    }

    @SerializedName(value = "id")
    val id: Int = - 1

    @SerializedName(value = "name")
    val name: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null
}
