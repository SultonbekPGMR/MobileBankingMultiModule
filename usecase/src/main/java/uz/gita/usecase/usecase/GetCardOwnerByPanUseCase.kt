package uz.gita.usecase.usecase

import kotlinx.coroutines.flow.Flow


/**
 * Created by Sultonbek Tulanov on 09/01/2025.
 */

interface GetCardOwnerByPanUseCase {
    operator fun invoke(pan: String): Flow<Result<String>>
}