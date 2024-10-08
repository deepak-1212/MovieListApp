package deepak.developer.movie_app.dependency_injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import deepak.developer.movie_app.data.network.MovieInterface
import deepak.developer.movie_app.data.network.NetworkConstants
import deepak.developer.movie_app.data.repositories.MovieDetailsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MovieHilt {

    @Singleton
    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        return Retrofit.Builder().baseUrl(NetworkConstants.MOVIE_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MovieInterface::class.java)
    }


    @Provides
    fun provideRepository(movieInterface: MovieInterface): MovieDetailsRepository {
        return MovieDetailsRepository(movieInterface)
    }

}