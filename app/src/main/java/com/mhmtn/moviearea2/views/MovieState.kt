package com.mhmtn.moviearea2.views

import com.mhmtn.moviearea2.models.Data
import com.mhmtn.moviearea2.models.MovieDetail


data class MovieState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1,
    val detailsData : MovieDetail = MovieDetail()
    )
