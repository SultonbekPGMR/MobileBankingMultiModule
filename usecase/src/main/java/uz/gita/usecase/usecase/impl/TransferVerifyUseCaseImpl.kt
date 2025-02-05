package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.flow.first
import uz.gita.entity.repository.TransferRepository
import uz.gita.usecase.usecase.TransferVerifyUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 11/01/2025.
 */

internal class TransferVerifyUseCaseImpl @Inject constructor(
    private val transferRepository: TransferRepository,
) : TransferVerifyUseCase {
    override suspend fun invoke(code: String): Result<Boolean> {
        return transferRepository.transferVerify(code).first()
    }
}