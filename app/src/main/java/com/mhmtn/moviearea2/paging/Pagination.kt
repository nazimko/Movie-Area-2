package com.mhmtn.moviearea2.paging

interface Pagination<Key,Item> {

    suspend fun loadNextPage()
    fun reset()

}