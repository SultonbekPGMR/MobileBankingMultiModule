package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingmultimodule.ui.screen.moneytransfer.TransferMoneyScreen
import uz.gita.presenter.contract.TransfersContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 31/01/2025.
 */

class TransfersDirections @Inject constructor(
    private val navigator:AppNavigator
):TransfersContract.Directions{
    override suspend fun navigateToMoneyTransferScreen() {
        navigator.navigateTo(TransferMoneyScreen())
    }
}