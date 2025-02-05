package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.gita.common.model.TransactionData
import uz.gita.entity.repository.TransferRepository
import uz.gita.usecase.usecase.GetLastRecipientsUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 15/01/2025.
 */

internal class GetLastRecipientsUseCaseImpl @Inject constructor(
    private val transferRepository: TransferRepository,
) : GetLastRecipientsUseCase {
    override fun invoke(): Flow<Result<List<TransactionData>>> = flow {
        transferRepository.getLastTransfers().onEach {
            emit(it)
        }.collect()

    }.flowOn(Dispatchers.IO)

}