package com.example.practice_project.presentation.coinList

import com.example.practice_project.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val error: String = ""
)
