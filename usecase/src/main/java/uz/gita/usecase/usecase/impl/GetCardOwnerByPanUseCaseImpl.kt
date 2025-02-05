package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import uz.gita.entity.model.request.CardPan
import uz.gita.entity.repository.TransferRepository
import uz.gita.usecase.usecase.GetCardOwnerByPanUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 09/01/2025.
 */

internal class GetCardOwnerByPanUseCaseImpl @Inject constructor(
    private val transferRepository: TransferRepository,
) : GetCardOwnerByPanUseCase {
    override fun invoke(pan: String): Flow<Result<String>> = flow {
        transferRepository.getOwnerByPan(CardPan(pan)).onEach { result ->
            result.getOrNull()?.let {
                emit(Result.success(it.pan))
            }
            result.exceptionOrNull()?.let {
                emit(Result.failure(it))
            }
        }.collect()
    }

}