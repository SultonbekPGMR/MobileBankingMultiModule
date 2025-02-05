package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Sultonbek Tulanov on 1/11/2025.
 */

interface TransferCompletedContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val btnAddState: Boolean  = false,
    )

    sealed interface Intent {
        data object OnNavigateToHomeClick : Intent
    }

    interface Directions {
        suspend fun navigateToHome()
    }
}
