package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingmultimodule.ui.screen.addcard.AddCardScreen
import uz.gita.mobilebankingmultimodule.ui.screen.moneytransfer.TransferMoneyScreen
import uz.gita.mobilebankingmultimodule.ui.screen.userdetails.UserDetailsScreen
import uz.gita.presenter.contract.HomeScreenContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

class HomeDirections @Inject constructor(
    private val navigator: AppNavigator,
): HomeScreenContract.Directions  {
    override suspend fun navigateToMoneyTransferScreen() {
        navigator.navigateTo(TransferMoneyScreen())
    }

    override suspend fun navigateToAddCardScreen() {
        navigator.navigateTo(AddCardScreen())
    }
    override suspend fun navigateToUserDetailsScreen() {
        navigator.navigateTo(UserDetailsScreen())
    }
}