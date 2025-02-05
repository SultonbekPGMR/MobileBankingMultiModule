package uz.gita.usecase.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.TransactionData


/**
 * Created by Sultonbek Tulanov on 15/01/2025.
 */

interface GetLastRecipientsUseCase {
    operator fun invoke(): Flow<Result<List<TransactionData>>>
}