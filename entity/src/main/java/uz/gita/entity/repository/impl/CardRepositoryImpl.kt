package uz.gita.entity.repository.impl

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.gita.common.util.handleApiCall
import uz.gita.entity.model.request.NewCardRequest
import uz.gita.entity.model.response.CardDataResponse
import uz.gita.entity.repository.CardRepository
import uz.gita.entity.remote.api.CardApi
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,

    ) : CardRepository {
    override fun add(newCardRequest: NewCardRequest): Flow<Result<Unit>> = handleApiCall(
        apiCall = {
            Log.d("TTOTOT", "called $newCardRequest")

            cardApi.addCard(newCardRequest)

        },
        onSuccess = {
            Log.d("TTOTOT", "addCard: $it")
            it.message
        },
        onError = {

        }
    )

    override fun getAll(): Flow<Result<List<CardDataResponse>>> = handleApiCall(
        apiCall = { cardApi.getAllCards() },
        onSuccess = {
            it
        }
    )


}