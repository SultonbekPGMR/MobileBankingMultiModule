package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import uz.gita.entity.model.request.NewCardRequest
import uz.gita.entity.repository.CardRepository
import uz.gita.usecase.usecase.AddCardUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal class AddCardUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository,
) : AddCardUseCase {
    override fun invoke(
        pan: String,
        expiredYear: String,
        expiredMonth: String,
        name: String,
    ): Flow<Result<Unit>> = flow {
        cardRepository.add(
            NewCardRequest(
                pan = pan,
                expiredYear = expiredYear,
                expiredMonth = expiredMonth,
                name = name
            )
        ).onEach {
            emit(it)
        }.collect()

    }.flowOn(IO)
}