package com.example.practice_project.data.data_source


import com.example.practice_project.data.data_source.dto.CoinDetailDTO.CoinDetailDTO
import com.example.practice_project.data.data_source.dto.CoinListDTO.CoinListDTO
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.Path

interface CoinGeckoApi {

    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&&sparkline=false")
    suspend fun getAllCoins(@Query(value = "page")page: String): CoinListDTO

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinById(@Path(value = "id")id: String): CoinDetailDTO
}
