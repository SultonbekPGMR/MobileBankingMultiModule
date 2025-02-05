package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.model.TransferData
import uz.gita.common.navigator.AppNavigator
import uz.gita.common.util.getCurrentDateTime
import uz.gita.mobilebankingauthcompose.ui.screen.auth.verify.VerifyContract
import uz.gita.mobilebankingmultimodule.ui.screen.auth.pincode.PinCodeScreen
import uz.gita.mobilebankingmultimodule.ui.screen.transfercomplete.TransferCompletedScreen
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class VerifyDirections @Inject constructor(
    private val navigator: AppNavigator,
) : VerifyContract.Directions {
    override suspend fun navigateToPinCode() {
        navigator.replaceAll(PinCodeScreen(newPinCode = true))
    }

    override suspend fun navigateToTransferCompleted(transferData: TransferData) {
        transferData.date = getCurrentDateTime()
        navigator.replaceAll(TransferCompletedScreen(transferData))
    }

    override suspend fun navigateBack() {
        navigator.back()

    }
}