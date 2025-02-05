package uz.gita.usecase.usecase.impl

import uz.gita.entity.repository.AuthRepository
import uz.gita.usecase.usecase.LogOutUserUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */

internal class LogOutUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : LogOutUserUseCase {
    override suspend fun invoke() {
       authRepository.logOutUser()

    }
}