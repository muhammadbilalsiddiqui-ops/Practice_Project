package presentation.coin

import androidx.lifecycle.ViewModel
import domain.use_cases.CoinDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import util.ResponseState
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val coinDetailUseCase: CoinDetailUseCase
) : ViewModel() {
    private val coinValue = MutableStateFlow(CoinState())
    val _coinValue : StateFlow<CoinState> = coinValue

    fun getCoinById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        coinDetailUseCase(id).collect {
            when (it) {
                is ResponseState.Success -> {
                    coinValue.value = CoinState(coinDetail = it.data)
                }
                is ResponseState.Loading -> {
                    coinValue.value = CoinState(isloading = true)
                }
                is ResponseState.Error -> {
                    coinValue.value = CoinState(error = it.message ?: "Error Occurred")
                }
            }
        }
    }
}