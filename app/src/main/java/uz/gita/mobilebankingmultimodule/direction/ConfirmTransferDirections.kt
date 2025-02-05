package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.model.TransferData
import uz.gita.common.model.VerifyType.Transfer
import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingmultimodule.ui.screen.auth.verify.VerifyScreen
import uz.gita.presenter.contract.ConfirmTransferScreenContract
import java.util.prefs.Preferences
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 10/01/2025.
 */

class ConfirmTransferDirections @Inject constructor(
    private val navigator: AppNavigator,
): ConfirmTransferScreenContract.Directions {
    override suspend fun popBack() {
        navigator.back()
    }

    override suspend fun navigateToVerify(phoneNumber:String, transferData: TransferData) {
        navigator.navigateTo(VerifyScreen( phoneNumber = phoneNumber,  Transfer(transferData)))
    }
}