package com.example.practice_project.presentation.coin

import com.example.practice_project.domain.model.CoinDetail

data class CoinState(
    val isloading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)


