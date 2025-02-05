package uz.gita.mobilebankingauthcompose.ui.screen.userdetails

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.presenter.util.AppConst.userDetailsScreenOptionsList


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

interface UserDetailsScreenContract {


    interface ViewModel {
        val uiState: StateFlow<UiState>
        val sideEffectFlow: Flow<SideEffect>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val userName: String = "",
        val availableOptions: List<OptionSettings> = userDetailsScreenOptionsList,
    )


    sealed interface Intent {
        data class OnOptionSelected(val option: OptionSettings) : Intent
        data object OnBtnBackClick : Intent
        data object LogOutUser : Intent
    }

    sealed interface SideEffect {
        data object ShowConfirmExitDialog : SideEffect
    }

    interface Directions {
        suspend fun popBack()
        suspend fun navigateToFirstScreen()
        suspend fun navigateToSettings()


    }


    data class OptionSettings(
        val iconId: Int,
        val titleId: Int,
    )


}