package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import uz.gita.entity.repository.TransferRepository
import uz.gita.entity.model.request.TransferRequest
import uz.gita.mobilebankingauthcompose.domain.usecase.TransferUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 11/01/2025.
 */

internal class TransferUseCaseImpl @Inject constructor(
    private val transferRepository: TransferRepository,
) : TransferUseCase {
    override fun invoke(
        type: String,
        senderId: String,
        receiverPan: String,
        amount: Int,
    ): Flow<Result<Unit>> = flow {
        transferRepository.transfer(
            TransferRequest(
                type = type,
                senderId = senderId,
                receiverPan = receiverPan,
                amount = amount
            )
        ).first().let {
            if (it.isSuccess) emit(Result.success(Unit))
            else emit(Result.failure(it.exceptionOrNull() ?: Throwable("Unknown error occurred ")))
        }
    }

}