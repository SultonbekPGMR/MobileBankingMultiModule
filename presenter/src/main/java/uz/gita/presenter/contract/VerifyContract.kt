package uz.gita.mobilebankingauthcompose.ui.screen.auth.verify

import kotlinx.coroutines.flow.StateFlow
import uz.gita.common.model.TransferData


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

interface VerifyContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }


    interface Directions {
        suspend fun navigateToPinCode()
        suspend fun navigateToTransferCompleted(transferData: TransferData)
        suspend fun navigateBack()
    }

    data class UiState(
        val btnConfirmState: Boolean = true,
        val resendTimerState: Int = 60,
    )


    sealed interface Intent {
        data class CheckCode(val phoneNumber: String, val code: String) : Intent
        data object ResendCode : Intent
        data object EditPhoneNumber : Intent
        data object PopBack : Intent
    }

}