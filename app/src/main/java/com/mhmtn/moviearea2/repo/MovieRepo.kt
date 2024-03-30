package com.mhmtn.moviearea2.repo

import com.mhmtn.moviearea2.models.MovieDetail
import com.mhmtn.moviearea2.models.MovieList
import com.mhmtn.moviearea2.util.Retrofit
import retrofit2.Response

class MovieRepo {

    suspend fun getMovieList(page:Int):Response<MovieList>{
        return Retrofit.api.getMovies(page)
    }

    suspend fun getMovieDetail(id : Int) : Response<MovieDetail>{
        return Retrofit.api.getMovieDetail(id)
    }
}