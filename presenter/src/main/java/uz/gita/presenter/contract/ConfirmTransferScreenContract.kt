package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow
import uz.gita.common.model.TransferData

/**
 * Created by Sultonbek Tulanov on 1/10/2025.
 */

interface ConfirmTransferScreenContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val btnConfirmState: Boolean = true,
    )

    sealed interface Intent {
        data object OnBackClick : Intent
        data class OnConfirmClick(val transferData: TransferData) : Intent
    }

    interface Directions {
        suspend fun popBack()
        suspend fun navigateToVerify(phoneNumber:String,transferData: TransferData)
    }
}
