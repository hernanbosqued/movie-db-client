package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName

data class ErrorsModel(@SerializedName("errors") val errors: List<ErrorModel>)