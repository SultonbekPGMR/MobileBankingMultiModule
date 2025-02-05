package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.model.TransferData
import uz.gita.common.navigator.AppNavigator
import uz.gita.common.util.formatBalance
import uz.gita.mobilebankingmultimodule.ui.screen.confirmpayment.ConfirmTransferScreen
import uz.gita.presenter.contract.TransferMoneyScreenContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

class TransferMoneyDirections @Inject constructor(
    private val navigator: AppNavigator,
) : TransferMoneyScreenContract.Directions {
    override suspend fun navigateToConfirmScreen(
        senderPan: String,
        senderId: String,
        senderBalance: String,
        receiverPan: String,
        receiverName: String,
        amount: Double,
        commission: Double,
    ) {
        navigator.navigateTo(
            ConfirmTransferScreen(
                transferData = TransferData(
                    senderPan = senderPan,
                    senderId = senderId,
                    senderBalance = senderBalance.reversed()
                        .chunked(3)
                        .joinToString(" ")
                        .reversed() + " UZS",
                    receiverPan = receiverPan,
                    receiverName = receiverName.uppercase(),
                    amount = formatBalance(amount),
                    total = amount + commission,
                    commission = formatBalance(commission)
                )
            )
        )
    }

    override suspend fun navigateToAllLastRecipientsScreen() {

    }

    override suspend fun popBack() {
        navigator.back()
    }


}