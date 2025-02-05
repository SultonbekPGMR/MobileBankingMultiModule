package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingauthcompose.ui.screen.main.MainScreen
import uz.gita.presenter.contract.PinCodeContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 01/01/2025.
 */

class PinCodeDirections @Inject constructor(
    private val navigator: AppNavigator,
): PinCodeContract.Directions {
    override suspend fun popBack() {
        navigator.back()
    }

    override suspend fun navigateToMainScreen() {
        navigator.replace(MainScreen())
    }

}