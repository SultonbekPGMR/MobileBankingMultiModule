package uz.gita.presenter.viewmodel

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.common.util.reduce
import uz.gita.presenter.contract.SignInContract
import uz.gita.presenter.contract.SignInContract.*
import uz.gita.presenter.contract.SignInContract.Intent.*
import uz.gita.usecase.usecase.ShowToastUseCase
import uz.gita.usecase.usecase.SignInUserUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */


class SignInViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase,
    private val showToastUseCase: ShowToastUseCase,
    private val directions: Directions,
) : SignInContract.ViewModel, ScreenModel {
    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is OpenRegisterScreen -> {
                screenModelScope.launch { directions.navigateToSignUp() }
            }

            is ValidateUser -> {
                    signInUserUseCase(intent.phoneNumber, intent.password)
                        .onStart {
                            Log.d("TTTTE", "onEventDispatcher: ")
                            uiState.reduce { it.copy(btnEnterState = false) }
                        }.onEach { result ->
                            if (result.isSuccess) directions.navigateToVerify(intent.phoneNumber)
                            else showToastUseCase(
                                result.exceptionOrNull()?.message
                                    ?: "Unknown error occurred. Try again"
                            )
                        }
                        .onCompletion {
                            uiState.reduce { it.copy(btnEnterState = true) }
                        }.launchIn(screenModelScope)



            }

            PopBack -> {
                screenModelScope.launch { directions.navigateBack() }
            }

        }
    }


}