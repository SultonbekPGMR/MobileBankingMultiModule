package uz.gita.entity.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.TransactionData
import uz.gita.entity.model.request.CalculateFeeRequest
import uz.gita.entity.model.request.CardPan
import uz.gita.entity.model.request.TransferRequest


/**
 * Created by Sultonbek Tulanov on 09/01/2025.
 */

interface TransferRepository {

    fun getOwnerByPan(cardPan: CardPan): Flow<Result<CardPan>>

    fun getFee(calculateFeeRequest: CalculateFeeRequest)

    fun transfer(transferRequest: TransferRequest): Flow<Result<Unit>>

    fun transferVerify(code: String): Flow<Result<Boolean>>

    fun resendTransferVerifyCode(): Flow<Result<Unit>>

    fun getTransferHistory(): Flow<PagingData<TransactionData>>
    fun getLastTransfers(): Flow<Result<List<TransactionData>>>


}