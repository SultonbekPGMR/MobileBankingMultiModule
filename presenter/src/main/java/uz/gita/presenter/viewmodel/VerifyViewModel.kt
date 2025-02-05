package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.hilt.ScreenModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.common.model.VerifyType
import uz.gita.common.model.VerifyType.Auth
import uz.gita.common.model.VerifyType.Transfer
import uz.gita.common.util.reduce
import uz.gita.mobilebankingauthcompose.ui.screen.auth.verify.VerifyContract.*
import uz.gita.mobilebankingauthcompose.ui.screen.auth.verify.VerifyContract.Intent.*
import uz.gita.usecase.usecase.*


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class VerifyViewModel @AssistedInject constructor(
    private val directions: Directions,
    private val verifyPhoneNumberUseCase: VerifyPhoneNumberUseCase,
    private val transferVerifyUseCase: TransferVerifyUseCase,
    private val resendVerificationCodeUseCase: ResendVerifyCodeUseCase,
    private val showToastUseCase: ShowToastUseCase,
    private val userPreferenceUseCase: UserPreferenceUseCase,
    @Assisted private val verifyType: VerifyType,
) : ViewModel, ScreenModel {
    @AssistedFactory
    interface Factory : ScreenModelFactory {
        fun create(verifyType: VerifyType): VerifyViewModel
    }

    override val uiState = MutableStateFlow(UiState())

    init {
        launchTimer()
    }


    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is CheckCode -> {
                screenModelScope.launch {
                    handleVerification(intent.phoneNumber, intent.code)
                }
            }

            is EditPhoneNumber -> {
                screenModelScope.launch {
                    directions.navigateBack()
                }
            }

            is ResendCode -> {
                screenModelScope.launch {
                    val result = resendVerificationCodeUseCase(verifyType)
                    if (result.isSuccess) {
                        showToastUseCase("Code was sent")
                        launchTimer(restart = true)
                    } else showToastUseCase(result.exceptionOrNull()?.message ?: "Try again")
                }

            }

            PopBack -> {
                screenModelScope.launch { directions.navigateBack() }
            }
        }

    }

    private suspend fun handleVerification(phoneNumber: String, code: String) {
        uiState.reduce { it.copy(btnConfirmState = false) }
        val result = getResult(code)
        uiState.reduce { it.copy(btnConfirmState = true) }
        result.getOrNull()?.let {
            if (it) {
                when (verifyType) {
                    is Auth -> {
                        userPreferenceUseCase.setUserPhoneNumber(phoneNumber)
                        directions.navigateToPinCode()
                    }

                    is Transfer -> {
                        directions.navigateToTransferCompleted(verifyType.transferData)
                    }
                }
            } else {
                showToastUseCase("code is incorrect")
            }

        }
        result.exceptionOrNull()?.message?.let { message ->
            showToastUseCase(message)
        }
    }


    private suspend fun getResult(code: String): Result<Boolean> {
        return when (verifyType) {
            is Auth -> {
                verifyPhoneNumberUseCase(verifyType.isSignInVerify, code)
            }

            is Transfer -> {
                transferVerifyUseCase(code)

            }
        }
    }

    private fun launchTimer(restart: Boolean = false) {
        screenModelScope.launch {
            if (restart) uiState.reduce { it.copy(resendTimerState = 60) }
            while (true) {
                if (uiState.value.resendTimerState < 1) return@launch
                delay(1000)
                uiState.reduce {
                    it.copy(resendTimerState = it.resendTimerState - 1)
                }
            }
        }
    }


}