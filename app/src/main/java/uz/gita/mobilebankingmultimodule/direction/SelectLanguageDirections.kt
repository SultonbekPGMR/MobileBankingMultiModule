package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingmultimodule.ui.screen.auth.signin.SignInScreen
import uz.gita.presenter.contract.SelectLanguageContract.Directions
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class SelectLanguageDirections @Inject constructor(
    private val navigator: AppNavigator,
) : Directions {
    override suspend fun navigateToSignIn() {
        navigator.navigateTo(SignInScreen())
    }

}