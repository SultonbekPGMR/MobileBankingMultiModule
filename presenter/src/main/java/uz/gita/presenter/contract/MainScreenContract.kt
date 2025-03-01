package uz.gita.presenter.contract

import kotlinx.coroutines.flow.StateFlow
import uz.gita.presenter.contract.MainScreenContract.BottomNavItem.Home
import uz.gita.presenter.R


/**
 * Created by Sultonbek Tulanov on 01/01/2025.
 */

interface MainScreenContract {


    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }


    enum class BottomNavItem(val iconId:Int, val selectedItemIconId:Int, val labelId: Int) {
        Home(R.drawable.ic_home, R.drawable.ic_home_green, R.string.lbl_home),
        Transfers(R.drawable.ic_transfer,R.drawable.ic_transfer_selected, R.string.lbl_transfers),
        Payments(R.drawable.ic_payment,R.drawable.ic_payment_selected, R.string.lbl_payments),
        Monitoring(R.drawable.ic_monitoring,R.drawable.ic_monitoring_selected, R.string.monitoring),
        Menu(R.drawable.ic_more,R.drawable.ic_more_selected, R.string.lbl_more);
    }

    sealed interface Intent {
        data class OnBottomNavClick(val bottomNavItem: BottomNavItem) : Intent
    }

    data class UiState(
        val currentScreen: BottomNavItem = Home,
    )

    interface Directions {

        suspend fun navigateBack()
    }

}