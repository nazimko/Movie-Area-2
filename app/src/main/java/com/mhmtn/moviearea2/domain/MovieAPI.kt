package com.mhmtn.moviearea2.domain

import com.mhmtn.moviearea2.models.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET ("movies")
    suspend fun getMovies(
        @Query("page") page : Int
    ): Response<MovieList>

 //https://moviesapi.ir/api/v1/movies?page=1

}