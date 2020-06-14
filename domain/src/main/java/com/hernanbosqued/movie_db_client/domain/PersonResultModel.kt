package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName
import com.hernanbosqued.movie_db_client.domain.Visitable
import com.hernanbosqued.movie_db_client.domain.Visitor
import java.io.Serializable


class PersonResultModel : ResultModel(), Serializable, Visitable {

    override fun visit(visitor: Visitor) {
        super.visit(visitor)
        visitor.accept(this)
    }

    @SerializedName("name")
    val name: String? = null

    @SerializedName("profile_path")
    val profilePath: String? = null
}
