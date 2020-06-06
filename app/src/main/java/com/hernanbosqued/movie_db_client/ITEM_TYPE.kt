package com.hernanbosqued.movie_db_client

enum class ITEM_TYPE(private val itemType: Int) {
    ROW_EVEN(0), ROW_ODD(1);

    companion object {
        @JvmStatic
        fun valueOf(itemType: Int): ITEM_TYPE {
            return values().first { itemType == it.itemType }
        }
    }
}