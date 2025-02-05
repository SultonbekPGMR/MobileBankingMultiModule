package uz.gita.mobilebankingauthcompose.domain.usecase

import kotlinx.coroutines.flow.Flow


/**
 * Created by Sultonbek Tulanov on 11/01/2025.
 */

interface TransferUseCase {
    operator fun invoke(
        type:String,
        senderId:String,
        receiverPan:String,
        amount:Int,
    ):Flow<Result<Unit>>
}