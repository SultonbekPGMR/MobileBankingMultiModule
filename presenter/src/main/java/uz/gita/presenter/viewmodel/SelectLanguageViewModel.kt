package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.presenter.contract.SelectLanguageContract
import uz.gita.presenter.contract.SelectLanguageContract.*
import uz.gita.presenter.contract.SelectLanguageContract.Intent.OpenNextScreen
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class SelectLanguageViewModel @Inject constructor(
    private val directions: Directions,
) : SelectLanguageContract.ViewModel, ScreenModel {
    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            OpenNextScreen -> {
                screenModelScope.launch {
                    uiState.value = uiState.value.copy(btnNextState = false)
                    directions.navigateToSignIn()
                    delay(500)
                    uiState.value = uiState.value.copy(btnNextState = true)

                }
            }
        }
    }


}