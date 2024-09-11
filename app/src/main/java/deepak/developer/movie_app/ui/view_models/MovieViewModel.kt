package deepak.developer.movie_app.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import deepak.developer.movie_app.MoviePaging
import deepak.developer.movie_app.data.models.MovieDetails
import deepak.developer.movie_app.data.network.MovieInterface
import deepak.developer.movie_app.data.repositories.MovieDetailsRepository
import deepak.developer.movie_app.utils.ApiResult
import deepak.developer.movie_app.utils.Events
import deepak.developer.movie_app.utils.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieInterface: MovieInterface,
    private val repository: MovieDetailsRepository
) : ViewModel() {

    private val query = MutableLiveData<String>()


    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(query, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Events<ApiResult<MovieDetails>>>()
    val movieDetails: LiveData<Events<ApiResult<MovieDetails>>> = _movieDetails


    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(ApiResult(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))

    }


}