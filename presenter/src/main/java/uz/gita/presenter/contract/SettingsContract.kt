package uz.gita.presenter.contract

import kotlinx.coroutines.Job
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract.OptionSettings
import uz.gita.presenter.AppViewModel
import uz.gita.presenter.util.AppConst.settingsScreenOptionsList

/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

interface SettingsContract {

    interface ViewModel : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent): Job
    }

    data class UiState(
        val options: List<OptionSettings> = settingsScreenOptionsList,
        val languageChanged: Boolean = false,
    )

    sealed interface SideEffect {
        data object OpenLanguageSelectionDialog : SideEffect
        data object OpenThemeSelectionDialog : SideEffect
    }

    sealed interface Intent {
        data object OnBackClick : Intent
        data class OnOptionSelected(val option: OptionSettings) : Intent
        data object OnLanguageChanged:Intent
        data object OnThemeChanged:Intent
    }

    interface Directions {
        suspend fun popBack()
    }
}
