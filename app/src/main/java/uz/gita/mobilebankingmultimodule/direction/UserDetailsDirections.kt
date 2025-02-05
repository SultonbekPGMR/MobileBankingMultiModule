package uz.gita.mobilebankingmultimodule.direction

import uz.gita.common.navigator.AppNavigator
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract
import uz.gita.mobilebankingmultimodule.ui.screen.auth.selectlanguage.SelectLanguageScreen
import uz.gita.mobilebankingmultimodule.ui.screen.settings.SettingsScreen
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

class UserDetailsDirections @Inject constructor(
    private val navigator: AppNavigator,
) : UserDetailsScreenContract.Directions {
    override suspend fun popBack() {
        navigator.back()
    }

    override suspend fun navigateToSettings() {
        navigator.navigateTo(SettingsScreen())
    }

    override suspend fun navigateToFirstScreen() {
        navigator.replaceAll(SelectLanguageScreen())
    }
}