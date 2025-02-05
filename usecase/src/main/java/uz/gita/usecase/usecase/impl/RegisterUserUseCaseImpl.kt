package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.RegisterUserData
import uz.gita.entity.repository.AuthRepository
import uz.gita.usecase.mapper.toUserRegisterRequest
import uz.gita.usecase.usecase.RegisterUserUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

internal class RegisterUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : RegisterUserUseCase {

    override fun invoke(userData: RegisterUserData): Flow<Result<Unit>> =
        authRepository.registerUser(userData.toUserRegisterRequest())

}