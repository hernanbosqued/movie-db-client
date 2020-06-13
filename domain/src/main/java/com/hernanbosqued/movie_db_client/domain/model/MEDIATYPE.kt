package com.hernanbosqued.movie_db_client.domain.model

import java.lang.RuntimeException
import java.lang.reflect.Type

enum class MEDIATYPE(val value: String) {
    MOVIE("movie") {
        override fun type(): Type {
            return MovieResultModel::class.java
        }
    },TV("tv") {
        override fun type(): Type {
            return TVResultModel::class.java
        }
    },PERSON("person") {
        override fun type(): Type {
            return PersonResultModel::class.java
        }
    },ERROR("error"){
        override fun type(): Type {
            throw RuntimeException("MEDIATYPE error")
        }
    };

    abstract fun type(): Type

    companion object{
        fun fromValue(param:String):MEDIATYPE{
            return values().firstOrNull{ it.value == param }?:ERROR
        }

        fun fromType(type: Type): MEDIATYPE {
            return values().firstOrNull{ it.type() == type }?:ERROR
        }
    }
}