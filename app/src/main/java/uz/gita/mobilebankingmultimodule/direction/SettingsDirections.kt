package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.presenter.contract.SettingsContract
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 31/01/2025.
 */

class SettingsDirections @Inject constructor(
    private val navigator: AppNavigator,
) : SettingsContract.Directions {
    override suspend fun popBack() {
        navigator.back()
    }
}