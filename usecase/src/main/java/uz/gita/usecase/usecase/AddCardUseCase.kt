package uz.gita.usecase.usecase

import kotlinx.coroutines.flow.Flow


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

interface AddCardUseCase {
    operator fun invoke(
        pan: String,
        expiredYear: String,
        expiredMonth: String,
        name: String,
    ): Flow<Result<Unit>>
}