package uz.gita.mobilebankingauthcompose.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.presenter.contract.TransferCompletedContract.*
import uz.gita.presenter.contract.TransferCompletedContract.Intent.OnNavigateToHomeClick
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 1/11/2025.
 */

class TransferCompletedViewModel @Inject constructor(
    private val directions: Directions,
) : ViewModel, ScreenModel {


    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            OnNavigateToHomeClick -> {screenModelScope.launch { directions.navigateToHome() }}
        }
    }
}
