package uz.gita.entity.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.entity.model.request.NewCardRequest
import uz.gita.entity.model.response.CardDataResponse


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

interface CardRepository {

    fun add(newCardRequest: NewCardRequest): Flow<Result<Unit>>
    fun getAll(): Flow<Result<List<CardDataResponse>>>

}