package uz.gita.presenter.contract

/**
 * Created by Sultonbek Tulanov on 1/18/2025.
 */

interface SplashContract {

    interface ViewModel {
        //        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    //    data class UiState(
//        val btnState: Boolean,
//    )

    sealed interface Intent {
        data object Navigate : Intent
    }

    interface Directions {
        suspend fun navigateToSelectLanguageScreen()
        suspend fun navigateToPinCodeScreen(isPinCodeSet: Boolean)
    }
}
