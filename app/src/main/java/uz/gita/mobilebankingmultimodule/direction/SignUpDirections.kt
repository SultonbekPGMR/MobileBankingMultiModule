package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.model.VerifyType.Auth
import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingmultimodule.ui.screen.auth.verify.VerifyScreen
import uz.gita.presenter.contract.SignUpContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class SignUpDirections @Inject constructor(
    private val navigator: AppNavigator
): SignUpContract.Directions {
    override suspend fun navigateToVerify(phoneNumber: String) {
        navigator.navigateTo(VerifyScreen(phoneNumber, Auth(false)    ))
    }

    override suspend fun navigateBack() {
        navigator.back()
    }


}