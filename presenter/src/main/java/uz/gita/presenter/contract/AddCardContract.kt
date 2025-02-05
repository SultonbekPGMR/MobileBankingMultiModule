package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Sultonbek Tulanov on 1/8/2025.
 */

interface AddCardContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val btnAddState: Boolean = true,
    )

    sealed interface Intent {
        data object OnBtnBackClick : Intent
        data class OnBtnAddCardClicked(
            val pan:String,
            val expiredDate:String,
            val name:String,
        ) : Intent
    }

    interface Directions {
        suspend fun popBack()
    }
}
