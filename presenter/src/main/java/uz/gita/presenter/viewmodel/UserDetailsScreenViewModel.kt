package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.gita.common.util.reduce
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract.*
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract.Intent.*
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract.SideEffect.ShowConfirmExitDialog
import uz.gita.presenter.R
import uz.gita.usecase.usecase.GetUserInfoUseCase
import uz.gita.usecase.usecase.LogOutUserUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

class UserDetailsScreenViewModel @Inject constructor(
    private val directions: Directions,
    private val logOutUserUseCase: LogOutUserUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,

    ) : UserDetailsScreenContract.ViewModel, ScreenModel {
    override val uiState = MutableStateFlow(UiState())
    private val sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    override val sideEffectFlow = sideEffectChannel.receiveAsFlow()

    init {
        screenModelScope.launch {
            getUserInfoUseCase()?.let { userData ->
                uiState.reduce { it.copy(userName = "${userData.firstName} ${userData.lastName}") }
            }
        }
    }


    override fun onEventDispatcher(intent: Intent) {
        screenModelScope.launch {
            when (intent) {
                LogOutUser -> {
                    logOutUserUseCase()
                    directions.navigateToFirstScreen()
                }

                OnBtnBackClick -> {
                    directions.popBack()
                }

                is OnOptionSelected -> {
                    handleOptionSelection(intent.option)
                }
            }
        }

    }

    private suspend fun handleOptionSelection(option: OptionSettings) {
        when (option.titleId) {
            R.string.lbl_settings -> {
              screenModelScope.launch {
                  directions.navigateToSettings()
              }
            }
            R.string.lbl_general_info -> {
                // Show general information
            }
            R.string.lbl_contact_the_bank -> {
                // Show contact options for the bank
            }
            R.string.invite_your_friend -> {
                // Initiate friend invitation process
            }
            R.string.lbl_exit -> {
                // Show exit confirmation dialog
                sideEffectChannel.send(ShowConfirmExitDialog)
            }
            else -> {
                // Handle any unexpected cases, if needed
            }
        }
    }

}