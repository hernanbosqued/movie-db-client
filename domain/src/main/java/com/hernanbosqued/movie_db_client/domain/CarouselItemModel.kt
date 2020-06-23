package com.hernanbosqued.movie_db_client.domain

import java.io.Serializable

class CarouselItemModel : Serializable{
    var id: Int = -1
    var hasVideo = false
    var overview: String? = null
    var title: String? = null
    var path: String? = null
    var ranking: String? = null
    var type: String? = null
}
