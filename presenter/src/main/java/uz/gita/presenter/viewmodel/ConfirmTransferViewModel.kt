package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.common.util.reduce
import uz.gita.mobilebankingauthcompose.domain.usecase.TransferUseCase
import uz.gita.presenter.contract.ConfirmTransferScreenContract.*
import uz.gita.presenter.contract.ConfirmTransferScreenContract.Intent.OnBackClick
import uz.gita.presenter.contract.ConfirmTransferScreenContract.Intent.OnConfirmClick
import uz.gita.usecase.usecase.ShowToastUseCase
import uz.gita.usecase.usecase.UserPreferenceUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 1/10/2025.
 */

class ConfirmTransferViewModel @Inject constructor(
    private val directions: Directions,
    private val transferUseCase: TransferUseCase,
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val showToastUseCase: ShowToastUseCase,
) : ViewModel, ScreenModel {


    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            OnBackClick -> {
                screenModelScope.launch { directions.popBack() }
            }

            is OnConfirmClick -> {
                transferUseCase(
                    type = "third-card",
                    senderId = intent.transferData.senderId,
                    receiverPan = intent.transferData.receiverPan,
                    amount = intent.transferData.total.toInt()
                ).onEach {
                    if (it.isSuccess) directions.navigateToVerify(
                        userPreferenceUseCase.getUserPhoneNumber(),
                        intent.transferData
                    )
                    else showToastUseCase(
                        it.exceptionOrNull()?.message ?: "Unknown error occurred. Try again later"
                    )
                }
                    .onStart { uiState.reduce { it.copy(btnConfirmState = false) } }
                    .onCompletion { uiState.reduce { it.copy(btnConfirmState = true) } }
                    .launchIn(screenModelScope)
            }
        }
    }
}
