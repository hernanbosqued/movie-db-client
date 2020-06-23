package com.hernanbosqued.movie_db_client.domain

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.reflect.Type

open class ResultModel : Visitable, Serializable {

    override fun visit(visitor: Visitor) {
        return visitor.accept(this)
    }

    override fun parse( ): CarouselItemModel {
        return CarouselItemModel().apply {
            this.ranking = this@ResultModel.ranking
            this.type = this@ResultModel.getType()
            this.hasVideo = this@ResultModel.hasVideo
            this.overview = this@ResultModel.overview
        }
    }

    @SerializedName("vote_average")
    val ranking: String = ""

    @SerializedName("overview")
    val overview: String = ""

    @SerializedName("video")
    var hasVideo: Boolean = false

    fun getType(): String {
        return MEDIATYPE.fromType(this.javaClass).toString()
    }

    object Deserializer : JsonDeserializer<ResultModel> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ResultModel? {

            val jsonObject = json!!.asJsonObject ?: run { return null }

            if (jsonObject.has("media_type")) {
                val type = MEDIATYPE.fromValue(jsonObject.get("media_type").asString).type()
                return Gson().fromJson<ResultModel>(jsonObject.toString(), type)
            }

            return null
        }
    }
}
