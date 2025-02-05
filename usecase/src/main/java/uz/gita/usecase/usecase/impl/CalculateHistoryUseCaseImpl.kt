package uz.gita.usecase.usecase.impl

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.flatMap
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import uz.gita.common.model.TransactionData
import uz.gita.usecase.usecase.CalculateHistoryUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

internal class CalculateHistoryUseCaseImpl @Inject constructor() : CalculateHistoryUseCase {
    override suspend fun invoke(
        transactions:Flow<PagingData<TransactionData>>,
        calculateIncome: Boolean,
    ): Double = withContext(Dispatchers.Default) {
//        if (calculateIncome) {
//
//            transactions.collectLatest {  }
//            transactions.last().filter { it.type == "income" }.let {
//            }
//
//
//        } else {
//            transactions.filter { it.type == "outcome" }.sumOf { it.amount }.toDouble()
//
//        }
        return@withContext 0.0
    }


}