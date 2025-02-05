package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingauthcompose.ui.screen.main.MainScreen
import uz.gita.presenter.contract.TransferCompletedContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 11/01/2025.
 */

class TransferCompletedDirections @Inject constructor(
    private val navigator: AppNavigator,
) : TransferCompletedContract.Directions {
    override suspend fun navigateToHome() {
        navigator.replaceAll(MainScreen())
    }


}