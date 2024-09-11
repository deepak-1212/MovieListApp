package deepak.developer.movie_app.data.repositories

import deepak.developer.movie_app.data.models.MovieDetails
import deepak.developer.movie_app.data.network.MovieInterface
import deepak.developer.movie_app.data.network.NetworkConstants
import deepak.developer.movie_app.utils.ApiResult
import deepak.developer.movie_app.utils.Status


class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId: String): ApiResult<MovieDetails> {

        return try {

            val response = movieInterface.getMovieDetails(imdbId, NetworkConstants.API_KEY)
            if (response.isSuccessful) {

                ApiResult(Status.SUCCESS, response.body(), null)

            } else {
                ApiResult(Status.ERROR, null, null)
            }

        } catch (e: Exception) {
            ApiResult(Status.ERROR, null, null)
        }


    }


}