package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.model.ResultModel

class DetailContract {
    interface View{

    }

    interface Presenter{
        fun getDetails(resultModel: ResultModel)
    }
}
