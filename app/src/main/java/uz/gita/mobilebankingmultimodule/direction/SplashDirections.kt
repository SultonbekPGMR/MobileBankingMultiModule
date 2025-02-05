package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingmultimodule.ui.screen.auth.pincode.PinCodeScreen
import uz.gita.mobilebankingmultimodule.ui.screen.auth.selectlanguage.SelectLanguageScreen
import uz.gita.presenter.contract.SplashContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 19/01/2025.
 */

class SplashDirections @Inject constructor(
    private val navigator: AppNavigator,
) : SplashContract.Directions {
    override suspend fun navigateToSelectLanguageScreen() {
        navigator.replaceAll(SelectLanguageScreen())
    }

    override suspend fun navigateToPinCodeScreen(isPinCodeSet: Boolean) {
        navigator.replaceAll(PinCodeScreen(!isPinCodeSet))
    }
}