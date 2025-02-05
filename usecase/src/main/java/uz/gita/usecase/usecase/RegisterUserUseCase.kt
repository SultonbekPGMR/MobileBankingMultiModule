package uz.gita.usecase.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.RegisterUserData


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

interface RegisterUserUseCase {
    operator fun invoke(userData: RegisterUserData): Flow<Result<Unit>>
}