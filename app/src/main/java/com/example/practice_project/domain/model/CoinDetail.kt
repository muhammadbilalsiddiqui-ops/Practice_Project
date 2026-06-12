package com.example.practice_project.domain.model

data class CoinDetail(
    val id: String,
    val name: String,
    val image: String,
    val market_cap: Double,
    val price: Double,
    val price_percentage_change: Double,
    val low_price: Double,
    val high_price: Double,
    val description: String,
)


