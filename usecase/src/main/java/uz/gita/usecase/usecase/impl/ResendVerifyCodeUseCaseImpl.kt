package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.flow.first
import uz.gita.common.model.VerifyType
import uz.gita.common.model.VerifyType.Auth
import uz.gita.common.model.VerifyType.Transfer
import uz.gita.entity.repository.AuthRepository
import uz.gita.entity.repository.TransferRepository
import uz.gita.usecase.usecase.ResendVerifyCodeUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */

internal class ResendVerifyCodeUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val transferRepository: TransferRepository,
) : ResendVerifyCodeUseCase {

    override suspend fun invoke(type: VerifyType): Result<Unit> = when (type) {
        is Auth -> {
            if (type.isSignInVerify) authRepository.resendSignInVerifyCode().first()
            else authRepository.resendRegistrationVerifyCode().first()
        }

        is Transfer -> {
            transferRepository.resendTransferVerifyCode().first()
        }
    }


}