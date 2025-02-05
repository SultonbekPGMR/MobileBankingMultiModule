package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.common.model.RegisterUserData
import uz.gita.common.util.reduce
import uz.gita.presenter.contract.SignUpContract
import uz.gita.presenter.contract.SignUpContract.*
import uz.gita.presenter.contract.SignUpContract.Intent.PopBack
import uz.gita.presenter.contract.SignUpContract.Intent.RegisterUser
import uz.gita.usecase.usecase.RegisterUserUseCase
import uz.gita.usecase.usecase.ShowToastUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 26/12/2024.
 */

class SignUpViewModel @Inject constructor(
    private val directions: Directions,
    private val registerUserUseCase: RegisterUserUseCase,
    private val showToastUseCase: ShowToastUseCase,
) : ViewModel, ScreenModel {
    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is RegisterUser -> {

                registerUserUseCase(
                    RegisterUserData(
                        phoneNumber = intent.phoneNumber,
                        name = intent.name,
                        surname = intent.surname,
                        age = intent.age,
                        gender = intent.gender,
                        password = intent.password
                    )
                )
                    .onStart {
                        uiState.reduce { it.copy(btnRegisterState = false) }
                    }
                    .onEach { result ->
                        if (result.isSuccess) directions.navigateToVerify(intent.phoneNumber)
                        else showToastUseCase(
                            result.exceptionOrNull()?.message ?: "Unknown error occurred. Try again"
                        )
                    }
                    .onCompletion {
                        uiState.reduce { it.copy(btnRegisterState = true) }
                    }.launchIn(screenModelScope)
            }

            PopBack -> {
                screenModelScope.launch {
                    directions.navigateBack()
                }
            }
        }
    }


}