package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import uz.gita.common.util.reduce
import uz.gita.presenter.contract.MainScreenContract
import uz.gita.presenter.contract.MainScreenContract.Intent
import uz.gita.presenter.contract.MainScreenContract.Intent.OnBottomNavClick
import uz.gita.presenter.contract.MainScreenContract.UiState
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 02/01/2025.
 */

class MainScreenViewModel @Inject constructor(

) : MainScreenContract
.ViewModel, ScreenModel {
    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is OnBottomNavClick -> {
                uiState.reduce { it.copy(currentScreen = intent.bottomNavItem) }
            }
        }
    }
}