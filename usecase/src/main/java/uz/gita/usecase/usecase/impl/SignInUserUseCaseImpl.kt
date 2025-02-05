package uz.gita.mobilebankingauthcompose.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.entity.model.request.UserSignInRequest
import uz.gita.entity.repository.AuthRepository
import uz.gita.usecase.usecase.SignInUserUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

internal class SignInUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : SignInUserUseCase {

    override fun invoke(phoneNumber: String, password: String): Flow<Result<Unit>> =
        authRepository.signInUser(UserSignInRequest(phoneNumber, password))


}