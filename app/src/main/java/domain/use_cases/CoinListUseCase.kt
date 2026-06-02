package domain.use_cases

import domain.model.Coin
import domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.http.HTTP
import util.ResponseState
import java.io.IOException
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
  private val repository: CoinRepository
) {
    operator fun invoke(page: String): Flow<ResponseState<List<Coin>>> = flow {
        try {
           emit(ResponseState.Loading<List<Coin>>())
            val coins = repository.getAllCoins(page = page).map { it.toCoin() }
            emit(ResponseState.Success<List<Coin>>(coins))
        }
        catch (e: HttpException){
            emit(ResponseState.Error<List<Coin>>(e.localizedMessage?:"An Unexpected Error"))
        }
        catch (e: IOException){
            emit(ResponseState.Error<List<Coin>>("Error Occurred"))
        }
    }
}