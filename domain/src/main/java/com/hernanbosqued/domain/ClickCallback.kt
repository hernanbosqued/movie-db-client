package com.hernanbosqued.domain

interface ClickCallback<T> {
    fun onClick(model: T)
}