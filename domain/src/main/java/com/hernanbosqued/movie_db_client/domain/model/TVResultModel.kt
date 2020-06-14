package com.hernanbosqued.movie_db_client.domain.model

import com.google.gson.annotations.SerializedName
import com.hernanbosqued.movie_db_client.domain.Visitable
import com.hernanbosqued.movie_db_client.domain.Visitor
import java.io.Serializable

class TVResultModel : ResultModel(MEDIATYPE.TV), Serializable, Visitable {

    override fun visit(visitor: Visitor) {
        super.visit(visitor)
        visitor.accept(this)
    }

    @SerializedName(value = "name")
    val name: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null
}
