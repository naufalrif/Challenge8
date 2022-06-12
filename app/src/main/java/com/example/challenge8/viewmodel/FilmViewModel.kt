package com.example.challenge8.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.challenge8.model.Movie
import com.example.challenge8.model.MovieResponse
import com.example.challenge8.network.APIServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(api: APIServices) : ViewModel() {
//    private var livedataFilm = MutableLiveData<List<GetAllFilmItem>>()
//    val film : LiveData<List<GetAllFilmItem>> = livedataFilm

    private var filmstate = MutableStateFlow(emptyList<Movie>())
    val datafilmstate: StateFlow<List<Movie>> get() = filmstate
    private val api = api
    private val apikey = "38c63d3167f5d3b3dc87620291bc2b1d"

//    fun getfilmLiveData(): MutableLiveData<List<GetAllFilmItem>> {
//        return livedataFilm
//    }

    init {
        val command = api.getMovie(apikey)
        command.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    filmstate.value = response.body()!!.results
                } else {
                    Log.e("view_model", response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("view_model_error", t.message.toString())
            }

        })
    }
}