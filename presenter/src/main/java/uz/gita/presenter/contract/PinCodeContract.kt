package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

interface PinCodeContract {
    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val typedNumCount: Int = 0,
        val buttonsClickableState: Boolean = true,
        val txtErrorState: Boolean = false,
        val biometricsState: Boolean = false,
    )

    sealed interface Intent {
        data class OnNumberClick(val number: Int) : Intent
        data object OnBackSpaceClick : Intent
        data class OnBiometricsClick(val newState: Boolean) : Intent
        data object PopBack : Intent
    }


    interface Directions {
        suspend fun popBack()
        suspend fun navigateToMainScreen()
    }

}