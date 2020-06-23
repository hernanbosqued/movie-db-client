package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class PersonResultModel : ResultModel(), Serializable, Visitable {

    override fun visit(visitor: Visitor) {
        super.visit(visitor)
        visitor.accept(this)
    }

    override fun parse( ): CarouselItemModel {
        return CarouselItemModel().apply {
            this.id = this@PersonResultModel.id
            this.hasVideo = this@PersonResultModel.hasVideo
            this.overview = this@PersonResultModel.overview
            this.title = this@PersonResultModel.name
            this.path = this@PersonResultModel.profilePath
            this.ranking = this@PersonResultModel.ranking
            this.type = this@PersonResultModel.getType()
        }
    }

    @SerializedName(value = "id")
    val id: Int = - 1

    @SerializedName("name")
    val name: String? = null

    @SerializedName("profile_path")
    val profilePath: String? = null
}
