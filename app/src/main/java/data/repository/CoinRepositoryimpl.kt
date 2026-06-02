package data.repository

import data.data_source.CoinGeckoApi
import data.data_source.dto.CoinDetailDTO.CoinDetailDTO
import data.data_source.dto.CoinListDTO.CoinListDTO
import javax.inject.Inject
import domain.repository.CoinRepository


class CoinRepositoryimpl @Inject constructor(
    private val coinApi: CoinGeckoApi
) : CoinRepository {


    override suspend fun getAllCoins(page: String): CoinListDTO {
        return coinApi.getAllCoins(page = page)
    }

    override suspend fun getCoinsById(id: String): CoinDetailDTO {
        return coinApi.getCoinById(id = id)
    }
}