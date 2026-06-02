package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import data.data_source.CoinGeckoApi
import data.repository.CoinRepositoryimpl
import domain.repository.CoinRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.Constants


@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoModule {

    @Provides
    @Singleton
    fun provideCoinGeckoApi(): CoinGeckoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinGeckoApi): CoinRepository{
        return CoinRepositoryimpl(api)
    }
}
