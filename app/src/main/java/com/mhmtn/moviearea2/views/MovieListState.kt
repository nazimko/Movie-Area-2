package com.mhmtn.moviearea2.views

import com.mhmtn.moviearea2.models.Data


data class MovieListState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1,

    )
