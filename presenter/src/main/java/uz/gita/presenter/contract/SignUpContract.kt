package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

interface SignUpContract {


    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)

    }
    interface Directions {
        suspend fun navigateToVerify(phoneNumber: String)
        suspend fun navigateBack()
    }


    sealed interface Intent {
        data class RegisterUser(
            val phoneNumber: String,
            val name: String,
            val surname: String,
            val age: String,
            val gender: String,
            val password: String,
        ) : Intent

        data object PopBack: Intent

    }

    data class UiState(
        val btnRegisterState: Boolean = true,
    )


}