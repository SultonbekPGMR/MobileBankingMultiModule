package uz.gita.usecase.usecase.impl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.TransactionData
import uz.gita.entity.repository.TransferRepository
import uz.gita.usecase.usecase.GetHistoryUseCase
import uz.gita.usecase.usecase.ShowToastUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

internal class GetHistoryUseCaseImpl @Inject constructor(
    private val transferRepository: TransferRepository,
    private val showToastUseCase: ShowToastUseCase,
) : GetHistoryUseCase {
    override  fun invoke(
        income: Boolean,
        expense: Boolean,
    ): Flow<PagingData<TransactionData>> = transferRepository.getTransferHistory()

}

//val list = result.getOrElse {
//    showToastUseCase(it.message ?: "Unknown Error Occurred")
//    return@withContext emptyList<TransactionData>()
//}
//
//val filteredList = when {
//    income -> list.filter { it.type == "income" }
//    expense -> list.filter { it.type == "outcome" }
//    else -> list
//}