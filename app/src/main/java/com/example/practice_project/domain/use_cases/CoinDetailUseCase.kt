package com.example.practice_project.domain.use_cases

import com.example.practice_project.domain.model.Coin
import com.example.practice_project.domain.model.CoinDetail
import com.example.practice_project.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.http.HTTP
import com.example.practice_project.util.ResponseState
import java.io.IOException
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<ResponseState<CoinDetail>> = flow {
        try {
            emit(ResponseState.Loading<CoinDetail>())
            val coinDetail = repository.getCoinsById(id).toCoinDetail()
            emit(ResponseState.Success<CoinDetail>(coinDetail))
        }
        catch (e: HttpException){
            emit(ResponseState.Error<CoinDetail>(e.localizedMessage?:"An Unexpected Error"))
        }
        catch (e: Exception){
            emit(ResponseState.Error<CoinDetail>(e.message ?: "An Error Occurred while mapping data"))
        }
    }
}
