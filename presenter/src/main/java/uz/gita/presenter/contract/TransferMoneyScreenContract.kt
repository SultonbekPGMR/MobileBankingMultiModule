package uz.gita.presenter.contract

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.common.model.CardData
import uz.gita.common.model.LastRecipient


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

interface TransferMoneyScreenContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val btnNextState: Boolean = true,
        val recipientName: String = "",
        val calculatedFee: Double = 0.00,
        val amount: Double = 0.00,
        val commission: Double = 0.00,
        val selectedCardIndex: Int = -1,
        val lastRecipientsCardsList: List<LastRecipient> = emptyList(),
        val userCardsList: List<CardData> = emptyList(),
    )

    sealed interface Intent {
        data class OnCardEntered(val card: String) : Intent
        data class OnAmountEntered(val amount: Double, val receiverPan: String) : Intent
        data class RecipientCardSelected(val cardData: CardData) : Intent
        data class OnCardToTransferFromSelected(val index: Int) : Intent
        data class OnLastRecipientSelected(val lastRecipient: LastRecipient) : Intent
        data object OnBtnNextClick : Intent
        data object OnBtnBackClick : Intent
        data object OnAmountEmpty : Intent
    }


    interface Directions {
        suspend fun navigateToConfirmScreen(
            senderPan: String,
            senderId: String,
            senderBalance: String,
            receiverPan: String,
            receiverName: String,
            amount: Double,
            commission: Double,
        )

        suspend fun navigateToAllLastRecipientsScreen()
        suspend fun popBack()

    }


}