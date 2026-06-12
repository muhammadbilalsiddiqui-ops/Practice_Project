package com.example.practice_project.domain.repository

import com.example.practice_project.data.data_source.dto.CoinDetailDTO.CoinDetailDTO
import com.example.practice_project.data.data_source.dto.CoinListDTO.CoinListDTO

interface CoinRepository {

    suspend fun getAllCoins(page: String): CoinListDTO

    suspend fun getCoinsById(id: String): CoinDetailDTO
}
