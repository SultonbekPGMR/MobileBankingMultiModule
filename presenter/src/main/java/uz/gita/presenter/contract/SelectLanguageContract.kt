package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

interface SelectLanguageContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    interface Directions {
        suspend fun navigateToSignIn()
    }


    data class UiState(
        val btnNextState: Boolean = true,
    )


    sealed interface Intent {
        data object OpenNextScreen : Intent
    }


}