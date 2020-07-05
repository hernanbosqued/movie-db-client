package com.hernanbosqued.movie_db_client.ui

import dagger.Component

@Component(modules = [MyModule::class])
interface MyComponent {
    fun poke(app: CarouselClient)
}
