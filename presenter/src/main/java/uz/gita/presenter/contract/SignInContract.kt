package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

interface SignInContract {

    sealed interface Intent {
        data class ValidateUser(val phoneNumber: String, val password: String) : Intent
        data object OpenRegisterScreen : Intent
        data object PopBack : Intent
    }


    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val btnEnterState: Boolean = true,
    )




    interface Directions {
        suspend fun navigateToSignUp()
        suspend fun navigateToVerify(phoneNumber: String)
        suspend fun navigateBack()
    }

}