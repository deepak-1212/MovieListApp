package deepak.developer.movie_app.utils

class Events<out T>(private val content: T) {


    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            content

        } else {
            null
        }
    }

    fun peekContent(): T = content
}

