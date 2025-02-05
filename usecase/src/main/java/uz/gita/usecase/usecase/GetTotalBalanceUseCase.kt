package uz.gita.usecase.usecase

import kotlinx.coroutines.flow.Flow


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

interface GetTotalBalanceUseCase {
    operator fun invoke(): Flow<Double>
}