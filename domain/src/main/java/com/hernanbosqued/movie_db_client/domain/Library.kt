package com.hernanbosqued.movie_db_client.domain

operator fun Set<MEDIATYPE>.get(item: MEDIATYPE): MEDIATYPE {
    return if (contains(item)) {
        elementAt(indexOf(item))
    } else MEDIATYPE.UNDEFINED
}

fun Set<MEDIATYPE>.getTypes(): String {
    return if (this[MEDIATYPE.MOVIE].isChecked() && this[MEDIATYPE.TV].isChecked()) "searchBoth"
    else if (this[MEDIATYPE.MOVIE].isChecked()) "searchMovies"
    else if (this[MEDIATYPE.TV].isChecked()) "searchTV"
    else ""
}

infix fun <T> T?.ifNull(block: () -> Unit): Unit? {
    return if (this == null) block()
    else null
}

//IF
infix fun <T> Boolean.then(ifResult: T?): Pair<Boolean, T?> {
    return Pair(this, ifResult)
}

//ELSE
infix fun <T> Pair<Boolean,T?>.orElse(elseResult: T?): T? {
    return if (this.first) {
        this.second
    } else {
        elseResult
    }
}