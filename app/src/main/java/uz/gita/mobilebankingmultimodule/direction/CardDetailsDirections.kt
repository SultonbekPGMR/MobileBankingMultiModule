package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.presenter.contract.CardDetailsContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 01/03/2025.
 */

class CardDetailsDirections @Inject constructor(
    private val navigator: AppNavigator
) :CardDetailsContract.Directions{
    override suspend fun popBack() {
        navigator.back()
    }
}