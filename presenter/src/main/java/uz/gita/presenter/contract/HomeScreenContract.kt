package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow
import uz.gita.common.model.CardData
import uz.gita.common.model.MainServiceData
import uz.gita.common.model.SuggestedActionBanner
import uz.gita.presenter.util.AppConst
import uz.gita.presenter.util.AppConst.bannerList


/**
 * Created by Sultonbek Tulanov on 02/01/2025.
 */

interface HomeScreenContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val userName:String = "",
        val totalBalance: Double = 0.00,
        var isRefreshing:Boolean = false,
        val mainServicesList: List<MainServiceData> = AppConst.mainMoneyServices,
        val userCardsList: List<CardData> = emptyList(),
        val suggestedActionsList: List<SuggestedActionBanner> = bannerList,
    )

    sealed interface Intent {
        data object OnTotalBalanceClick : Intent
        data object OnBalanceEyeClick : Intent
        data object OnAddCardClick : Intent
        data object OnAllCardsClick : Intent
        data object OnSearchClick : Intent
        data object OnProfileClick : Intent
        data object OnRefresh : Intent
        data class OnServiceClicked(val service: MainServiceData): Intent
        data class OnSupportClick(val number: Int) : Intent
        data class OnNotificationClick(val newState: Boolean) : Intent
    }


    interface Directions {
        suspend fun navigateToMoneyTransferScreen()
        suspend fun navigateToAddCardScreen()
        suspend fun navigateToUserDetailsScreen()

    }


}