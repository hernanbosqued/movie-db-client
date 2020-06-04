package com.hernanbosqued.domain

interface Client {
    fun get(page: Int, callback: ClientCallbacks)
}