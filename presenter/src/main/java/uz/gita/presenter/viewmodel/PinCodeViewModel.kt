package uz.gita.presenter.viewmodel

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.hilt.ScreenModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.common.util.reduce
import uz.gita.presenter.contract.PinCodeContract.*
import uz.gita.presenter.contract.PinCodeContract.Intent.*
import uz.gita.usecase.usecase.ShowToastUseCase
import uz.gita.usecase.usecase.UserPreferenceUseCase


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class PinCodeViewModel @AssistedInject constructor(
    private val directions: Directions,
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val showToastUseCase: ShowToastUseCase,
    @Assisted private val newPinCode: Boolean,
) : ViewModel, ScreenModel {

    @AssistedFactory
    interface Factory : ScreenModelFactory {
        fun create(newPinCode: Boolean): PinCodeViewModel
    }

    override val uiState =
        MutableStateFlow(UiState(biometricsState = userPreferenceUseCase.isBiometricsEnabled()))
    private var typedNumbers: StringBuilder = StringBuilder("")
    private var firstTimeCompleted = false
    private var pinCode = ""


    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            OnBackSpaceClick -> {
                if (typedNumbers.isEmpty()) return
                uiState.reduce { it.copy(buttonsClickableState = false) }
                typedNumbers.deleteAt(typedNumbers.lastIndex)
                uiState.reduce {
                    it.copy(
                        typedNumCount = typedNumbers.length,
                        buttonsClickableState = true
                    )
                }

                Log.d("BBBBHHH", "onBackSpaceClick: $typedNumbers")
            }

            is OnNumberClick -> {
                handleClick(intent.number)
            }

            is OnBiometricsClick -> {
                uiState.reduce { it.copy(biometricsState = intent.newState) }
                userPreferenceUseCase.setBiometricsChange(uiState.value.biometricsState)
            }

            PopBack -> {}

        }
    }

    private fun handleClick(number: Int) {

        screenModelScope.launch {
            if (typedNumbers.length == 4) return@launch
            uiState.reduce { it.copy(buttonsClickableState = false) }
            typedNumbers.append(number)
            uiState.reduce { it.copy(typedNumCount = typedNumbers.length) }
            if (typedNumbers.length == 4) delay(50)
            Log.d("BBBBHHH", "onNumberClick: $typedNumbers")
            if (!newPinCode && typedNumbers.length == 4) {
                if (userPreferenceUseCase.checkPinCode(typedNumbers.toString())) directions.navigateToMainScreen()
                else {
                    showToastUseCase("Wrong passcode")
                    typedNumbers.clear()
                    uiState.reduce { it.copy(typedNumCount = 0) }
                }

                uiState.reduce { it.copy(buttonsClickableState = true) }
                return@launch
            }
            if (typedNumbers.length == 4 && !firstTimeCompleted) {
                firstTimeCompleted = true
                pinCode = typedNumbers.toString()
                typedNumbers.clear()
                uiState.reduce { it.copy(typedNumCount = 0) }
            }

            if (typedNumbers.length == 4 && firstTimeCompleted) {

                if (typedNumbers.toString() != pinCode) {
                    uiState.reduce { it.copy(txtErrorState = true) }
                    delay(500L)
                    typedNumbers.clear()
                    uiState.reduce { it.copy(typedNumCount = 0) }
                    delay(200)
                    uiState.reduce { it.copy(txtErrorState = false) }
                } else {
                    userPreferenceUseCase.setPinCode(pinCode)
                    directions.navigateToMainScreen()
                }
            }

            uiState.reduce { it.copy(buttonsClickableState = true) }

        }

    }


}