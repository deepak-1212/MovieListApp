package deepak.developer.movie_app.data.repositories

import deepak.developer.movie_app.BuildConfig
import deepak.developer.movie_app.data.models.MovieDetails
import deepak.developer.movie_app.data.network.MovieInterface
import deepak.developer.movie_app.utils.ApiResult


class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId: String): ApiResult<MovieDetails> {
        return try {
            val response = movieInterface.getMovieDetails(imdbId, BuildConfig.MOVIE_API_KEY)
            if (response.isSuccessful) {
                ApiResult.success(response.body())
            } else {
                ApiResult.error("Something went wrong")
            }
        } catch (e: Exception) {
            ApiResult.error(e.message)
        }
    }

}