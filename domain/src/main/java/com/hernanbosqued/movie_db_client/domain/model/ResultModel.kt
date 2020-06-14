package com.hernanbosqued.movie_db_client.domain.model

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.hernanbosqued.movie_db_client.domain.Visitable
import com.hernanbosqued.movie_db_client.domain.Visitor
import java.io.Serializable
import java.lang.reflect.Type

open class ResultModel(mediatype: MEDIATYPE) : Visitable, Serializable {

    init {
        Deserializer.mediatype = mediatype
    }

    fun getMediaType(): MEDIATYPE {
        return MEDIATYPE.fromType(this.javaClass)
    }

    override fun visit(visitor: Visitor) {
        visitor.accept(this)
    }

    @SerializedName(value = "id")
    val id: Int = -1

    @SerializedName("vote_average")
    val ranking: String = ""

    @SerializedName("overview")
    val overview: String = ""

    @SerializedName("video")
    var hasVideo: Boolean = false

    object Deserializer : JsonDeserializer<ResultModel> {

        var mediatype = MEDIATYPE.UNDEFINED

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
