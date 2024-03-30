package com.mhmtn.moviearea2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmtn.moviearea2.repo.MovieRepo
import com.mhmtn.moviearea2.views.MovieListState
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    private val repo = MovieRepo()
    var state by mutableStateOf(MovieListState())
    init {
        viewModelScope.launch {
           val response = repo.getMovieList(state.page)
            state = state.copy(
                movies = response.body()!!.data
            )
        }
    }
}