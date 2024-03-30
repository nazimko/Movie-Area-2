package com.mhmtn.moviearea2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmtn.moviearea2.repo.MovieRepo
import com.mhmtn.moviearea2.views.MovieState
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repo = MovieRepo()
    var state by mutableStateOf(MovieState())
    var id by mutableIntStateOf(0)
    init {
        viewModelScope.launch {
           val response = repo.getMovieList(state.page)
            state = state.copy(
                movies = response.body()!!.data
            )
        }
    }

    fun getMovieDetail(){
        viewModelScope.launch {
            try {
                val response = repo.getMovieDetail(id = id)
                if (response.isSuccessful){
                    state = state.copy(detailsData = response.body()!!)
                }
            }catch (e:Exception){

            }
        }
    }

    fun getMovieListBySearch(query : String){
        viewModelScope.launch {
            try {
                val response = repo.getMovieListBySearch(query = query)
                if (response.isSuccessful){
                    state = state.copy(movies = response.body()!!.data)
                }
            } catch (e:Exception){

            }
        }
    }

}