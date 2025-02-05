package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.model.VerifyType.Auth
import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingmultimodule.ui.screen.auth.signup.SignUpScreen
import uz.gita.mobilebankingmultimodule.ui.screen.auth.verify.VerifyScreen
import uz.gita.presenter.contract.SignInContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class SignInDirections @Inject constructor(
    private val navigator: AppNavigator,
) : SignInContract.Directions {
    override suspend fun navigateToSignUp() {
        navigator.navigateTo(SignUpScreen())
    }

    override suspend fun navigateToVerify(phoneNumber: String) {
        navigator.navigateTo(VerifyScreen(phoneNumber, Auth(true)))
    }

    override suspend fun navigateBack() {
        navigator.back()
    }

}