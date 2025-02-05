package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.flow.first
import uz.gita.entity.repository.AuthRepository
import uz.gita.usecase.usecase.VerifyPhoneNumberUseCase
import javax.inject.Inject

/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */


internal class VerifyPhoneNumberUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : VerifyPhoneNumberUseCase {
    override suspend fun invoke(isSignInVerify: Boolean, code: String): Result<Boolean> {
        return if (isSignInVerify) authRepository.verifySignInPhoneNumber(code).first()
        else authRepository.verifyRegisterPhoneNumber(code).first()
    }

}