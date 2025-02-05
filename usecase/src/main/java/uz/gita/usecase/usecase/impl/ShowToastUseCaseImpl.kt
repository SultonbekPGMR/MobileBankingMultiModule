package uz.gita.usecase.usecase.impl

import uz.gita.usecase.usecase.ShowToastUseCase
import uz.gita.common.navigator.AppNavigator
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */

internal class ShowToastUseCaseImpl @Inject constructor(
    private val appNavigationDispatcher: AppNavigator,
) : ShowToastUseCase {
    override suspend fun invoke(message: String) {
        appNavigationDispatcher.toast(message)
    }
}