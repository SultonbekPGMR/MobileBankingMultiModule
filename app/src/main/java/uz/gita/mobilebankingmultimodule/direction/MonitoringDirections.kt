package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.presenter.contract.MonitoringScreenContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

class MonitoringDirections @Inject constructor(
    private val navigator: AppNavigator,
): MonitoringScreenContract.Directions {
    override suspend fun popBack() {

    }
}