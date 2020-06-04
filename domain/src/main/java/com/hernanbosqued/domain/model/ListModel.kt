package com.hernanbosqued.domain.model

import com.google.gson.annotations.SerializedName

data class ListModel(@SerializedName("results") val results: MutableList<ResultModel>)