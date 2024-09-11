package deepak.developer.movie_app.data.models

data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)