package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingauthcompose.ui.screen.main.MainScreen
import uz.gita.presenter.contract.AddCardContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

class AddCardDirections @Inject constructor(
    private val navigator: AppNavigator,
) : AddCardContract.Directions {
    override suspend fun popBack() {
        navigator.replaceAll(MainScreen())
    }
}