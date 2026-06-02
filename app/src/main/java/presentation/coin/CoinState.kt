package presentation.coin

import domain.model.CoinDetail

data class CoinState(
    val isloading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)

