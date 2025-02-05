package uz.gita.usecase.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.TransactionData


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

interface GetHistoryUseCase {
     operator fun invoke(
        income: Boolean = false,
        expense: Boolean = false,
    ): Flow<PagingData<TransactionData>>
}