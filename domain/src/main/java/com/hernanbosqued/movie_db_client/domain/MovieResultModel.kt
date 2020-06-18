package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName
import com.hernanbosqued.movie_db_client.domain.Visitable
import com.hernanbosqued.movie_db_client.domain.Visitor
import java.io.Serializable


class MovieResultModel : ResultModel(), Serializable, Visitable {

    override fun visit(visitor: Visitor) {
        super.visit(visitor)
        visitor.accept(this)
    }

    override fun parse( ): CarouselItemModel {
        return CarouselItemModel().apply {
            this.title = title
            this.path = posterPath
            this.ranking = ranking
            this.type = getType()
        }
    }


    @SerializedName(value = "title")
    val title: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null
}
