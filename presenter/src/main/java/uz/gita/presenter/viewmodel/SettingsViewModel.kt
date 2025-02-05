package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract.OptionSettings
import uz.gita.presenter.R
import uz.gita.presenter.contract.SettingsContract.*
import uz.gita.presenter.contract.SettingsContract.Intent.*
import uz.gita.presenter.contract.SettingsContract.SideEffect.OpenLanguageSelectionDialog
import uz.gita.presenter.contract.SettingsContract.SideEffect.OpenThemeSelectionDialog
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

class SettingsViewModel @Inject constructor(
    private val directions: Directions,
) : ViewModel {

    override val container: Container<UiState, SideEffect> = container(UiState())


    override fun onEventDispatcher(intent: Intent) = intent {
        when (intent) {
            OnBackClick -> {
                screenModelScope.launch {
                    directions.popBack()
                }
            }

            OnLanguageChanged -> {
                reduce { state.copy(languageChanged = !state.languageChanged) }
            }

            is OnOptionSelected -> {
                handleOptionSelection(intent.option)
            }

            OnThemeChanged -> {
                state.copy(languageChanged = !state.languageChanged)
            }
        }
    }


    private fun handleOptionSelection(option: OptionSettings) = intent {
        when (option.titleId) {
            R.string.security -> {
                // Handle security option click
            }

            R.string.widget_settings -> {
                // Handle widget settings option click
            }

            R.string.theme -> postSideEffect(OpenThemeSelectionDialog)

            R.string.lbl_language -> postSideEffect(OpenLanguageSelectionDialog)


            R.string.about_app -> {
                // Handle about app option click
            }

            else -> {}
        }
    }

}
