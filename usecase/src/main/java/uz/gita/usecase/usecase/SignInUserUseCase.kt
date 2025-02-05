package uz.gita.usecase.usecase

import kotlinx.coroutines.flow.Flow


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

interface SignInUserUseCase {
    operator fun invoke(phoneNumber: String, password: String): Flow<Result<Unit>>
}