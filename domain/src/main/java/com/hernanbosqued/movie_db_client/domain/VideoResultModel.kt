package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName

class VideoResultModel : ResultModel(), Visitable {

    override fun visit(visitor: Visitor) {
        super.visit(visitor)
        visitor.accept(this)
    }

    @SerializedName(value = "id")
    override val id: String = ""

    @SerializedName(value = "key")
    val key: String? = null

    @SerializedName(value = "name")
    val name: String? = null

    @SerializedName("site")
    val site: String? = null

    @SerializedName("size")
    val size: Int? = null
}
