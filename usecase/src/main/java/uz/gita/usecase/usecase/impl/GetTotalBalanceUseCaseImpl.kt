package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.gita.entity.repository.CardRepository
import uz.gita.usecase.usecase.GetTotalBalanceUseCase
import javax.inject.Inject
import kotlin.collections.forEach


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal class GetTotalBalanceUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository,
) : GetTotalBalanceUseCase {
    override fun invoke(): Flow<Double> = flow<Double> {
        val result = cardRepository.getAll().first()
        var sum = 0.00
       result.getOrNull()?.let {cards->
           cards.forEach {
               sum += it.amount
           }
       }

        emit(sum)

    }.flowOn(Dispatchers.Default)

}