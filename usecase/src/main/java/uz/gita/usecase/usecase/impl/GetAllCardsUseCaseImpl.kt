package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.common.model.CardData
import uz.gita.entity.repository.CardRepository
import uz.gita.usecase.mapper.toCardData
import uz.gita.usecase.usecase.GetAllCardsUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal class GetAllCardsUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository,
) : GetAllCardsUseCase {
    override operator fun invoke(): Flow<Result<List<CardData>>> = flow {
        emit(
            cardRepository.getAll().first()
                .map { responseList -> responseList.map { it.toCardData() } })

    }.flowOn(IO)


}