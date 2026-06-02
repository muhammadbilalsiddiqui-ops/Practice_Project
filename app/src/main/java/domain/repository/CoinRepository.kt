package domain.repository

import data.data_source.dto.CoinDetailDTO.CoinDetailDTO
import data.data_source.dto.CoinListDTO.CoinListDTO

interface CoinRepository {

    suspend fun getAllCoins(page: String): CoinListDTO

    suspend fun getCoinsById(id: String): CoinDetailDTO
}