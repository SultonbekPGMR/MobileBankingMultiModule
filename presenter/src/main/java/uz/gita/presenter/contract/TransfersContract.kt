package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow
import uz.gita.common.model.MainServiceData
import uz.gita.presenter.util.AppConst

/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

interface TransfersContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val options:List<MainServiceData> = AppConst.transferScreenOptions,
    )

    sealed interface Intent {
        data class OnOptionSelected(val mainServiceData: MainServiceData) : Intent
    }

    interface Directions {
        suspend fun navigateToMoneyTransferScreen()
    }
}
