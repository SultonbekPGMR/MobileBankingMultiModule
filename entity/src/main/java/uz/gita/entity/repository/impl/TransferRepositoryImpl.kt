package uz.gita.entity.repository.impl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.gita.common.model.TransactionData
import uz.gita.common.util.handleApiCall
import uz.gita.entity.local.Preferences
import uz.gita.entity.model.request.*
import uz.gita.entity.remote.api.TransferApi
import uz.gita.entity.repository.TransferRepository
import uz.gita.entity.util.MonitoringDataSource
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 09/01/2025.
 */

internal class TransferRepositoryImpl @Inject constructor(
    private val transferApi: TransferApi,
    private val preferences: Preferences,
) : TransferRepository {
    override fun getOwnerByPan(cardPan: CardPan): Flow<Result<CardPan>> = handleApiCall(
        apiCall = {
            transferApi.getOwnerNameByCardPan(cardPan)
        },
        onSuccess = {
            it
        },
    )

    override fun getFee(calculateFeeRequest: CalculateFeeRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            transferApi.getCalculatedFee(calculateFeeRequest)
        }

    }

    override fun transfer(transferRequest: TransferRequest): Flow<Result<Unit>> = handleApiCall(
        apiCall = {
            transferApi.transfer(transferRequest)
        },
        onSuccess = {
            Log.d("UIUIUIUIUIUIUOOOIOIOI", "onSuccess: ${it.token}")

            preferences.verificationToken = it.token
        },
        onError = {
            Log.d("UIUIUIUIUIUIUOOOIOIOI", "onError: $it ")
        }
    )

    override fun transferVerify(code: String): Flow<Result<Boolean>> = handleApiCall(
        apiCall = {
            transferApi.verify(VerifyRequest(preferences.verificationToken, code))
        },
        onSuccess = {
            true
        }
    )

    override fun resendTransferVerifyCode(): Flow<Result<Unit>> = handleApiCall(
        apiCall = { transferApi.resendSignUpCode(ResendCodeRequest(preferences.verificationToken)) },
        onSuccess = {
            preferences.verificationToken = it.token
        }
    )


    override fun getTransferHistory(): Flow<PagingData<TransactionData>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 10,
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { MonitoringDataSource(transferApi) }
        ).flow
    }

    override fun getLastTransfers(): Flow<Result<List<TransactionData>>> = handleApiCall(
        apiCall = {
            transferApi.getLastTransfers()
        },
        onSuccess = { list ->
            list.forEach {
                Log.d("KFMKFMFKMFk", "getLastTransfers: ${it}")
            }
            list.filter { it.type == "outcome" }
        }
    )


}