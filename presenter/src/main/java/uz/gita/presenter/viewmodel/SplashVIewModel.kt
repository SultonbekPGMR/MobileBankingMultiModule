package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import uz.gita.presenter.contract.SplashContract.*
import uz.gita.presenter.contract.SplashContract.Intent.Navigate
import uz.gita.usecase.usecase.UserPreferenceUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class SplashVIewModel @Inject constructor(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val directions: Directions,
) : ViewModel, ScreenModel {


    private fun navigate() {
        screenModelScope.launch {
            if (!userPreferenceUseCase.isUserSignedIn()) {
                directions.navigateToSelectLanguageScreen()
            } else {
                directions.navigateToPinCodeScreen(userPreferenceUseCase.isPinCodeSet())
            }

        }
    }

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            Navigate -> {
                navigate()
            }
        }
    }

}