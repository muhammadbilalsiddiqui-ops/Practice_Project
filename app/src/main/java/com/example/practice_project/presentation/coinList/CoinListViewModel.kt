package com.example.practice_project.presentation.coinList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project.domain.use_cases.CoinListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.practice_project.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinListUseCase: CoinListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val coinListValue: StateFlow<CoinListState> = _state

    fun getAllCoins(page: String)= viewModelScope.launch(Dispatchers.IO) {
        coinListUseCase(page = page).collect {
            when (it) {
                is ResponseState.Success -> {
                    _state.value = CoinListState(coinList = it.data ?: emptyList())
                }

                is ResponseState.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is ResponseState.Error -> {
                    _state.value = CoinListState(error = it.message ?: "Error Occurred")
                }
            }
        }
    }
}

