package uz.gita.presenter.contract

import kotlinx.coroutines.Job
import uz.gita.common.model.CardData
import uz.gita.presenter.AppViewModel
import uz.gita.presenter.contract.SettingsContract.*

/**
 * Created by Sultonbek Tulanov on 3/1/2025.
 */

interface CardDetailsContract {

    interface ViewModel : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent): Job
    }

    data class UiState(
        val cardData: CardData = CardData(
            id = 1,
            name = "Personal Card",
            amount = 5000000L,
            owner = "Sultonbek Tulanov",
            pan = "8600123456789012",
            expiredYear = 2026,
            expiredMonth = 12,
            themeType = 1,
            isVisible = true,
            bankName = "Kapital Bank"
        ),
    )

    sealed interface SideEffect {
        data object OpenMoreOptionsDialog : SideEffect
    }


    sealed interface Intent {
        data object OnBackClick : Intent
    }

    interface Directions {
        suspend fun popBack()
    }
}
