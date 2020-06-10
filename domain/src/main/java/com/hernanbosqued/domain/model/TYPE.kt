package com.hernanbosqued.domain.model

enum class TYPE(var value: String) {
    MOVIE("movie") {
        override fun geLabel(model: ResultModel): String {
            return model.title ?: ""
        }
    },
    TV_SHOW("tv") {
        override fun geLabel(model: ResultModel): String {
            return model.name ?: ""
        }
    },
    PERSON("person") {
        override fun geLabel(model: ResultModel): String {
            return model.name ?: ""
        }
    },
    ERROR("error") {
        override fun geLabel(model: ResultModel): String {
            return "error"
        }
    };

    abstract fun geLabel(model: ResultModel): String

    companion object {
        fun resolve(model: ResultModel): TYPE = when {
            model.posterPath == null -> PERSON
            model.name != null -> TV_SHOW
            model.title != null -> MOVIE
            else -> ERROR
        }

    }
}