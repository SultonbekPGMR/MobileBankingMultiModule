package uz.gita.presenter.viewmodel

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.common.model.LastRecipient
import uz.gita.common.model.TransactionData
import uz.gita.common.util.reduce
import uz.gita.presenter.contract.TransferMoneyScreenContract.*
import uz.gita.presenter.contract.TransferMoneyScreenContract.Intent.*
import uz.gita.usecase.usecase.*
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

class TransferMoneyScreenViewModel @Inject constructor(
    private val directions: Directions,
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getCardOwnerByPanUseCase: GetCardOwnerByPanUseCase,
    private val showToastUseCase: ShowToastUseCase,
    private val calculateCommissionUseCase: CalculateCommissionUseCase,
    private val getLastRecipientsUseCase: GetLastRecipientsUseCase,
) : ViewModel, ScreenModel {
    override val uiState = MutableStateFlow(UiState())

    private var recipientCardPan: String = ""

    init {
        Log.d("NJNJDNJNDJNDJDNJDN", ": go")
        getLastRecipientsUseCase()
            .onEach { result ->
                result.getOrNull()?.let { list ->
                    uiState.reduce {
                        it.copy(
                            lastRecipientsCardsList = list.map {
                                LastRecipient(
                                    pan = it.to,
                                    time = it.time,
                                )
                            }
                        )
                    }
                }


            }
            .launchIn(screenModelScope)
        getLastRecipientsUseCase().onEach { result ->
            val list: List<TransactionData> = result.getOrElse {
                Log.d("NJNJDNJNDJNDJDNJDN", ": ${it.message}")
                emptyList()
            }

            list.forEach {
                Log.d("NJNJDNJNDJNDJDNJDN", ": $it")

            }

        }.launchIn(screenModelScope)


        getAllCardsUseCase()
            .onEach { result ->
                result.getOrNull()?.let { cards ->
                    uiState.reduce {
                        it.copy(
                            userCardsList = cards,
                            selectedCardIndex = if (cards.isNotEmpty()) 0 else -1
                        )
                    }
                }
            }.launchIn(screenModelScope)

    }


    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is OnCardEntered -> {
                if (intent.card.length != 16) {
                    uiState.reduce { it.copy(recipientName = "") }
                    return
                }
                recipientCardPan = intent.card
                getCardOwnerByPanUseCase(intent.card).onEach { result ->
                    result.getOrNull()?.let { owner ->
                        uiState.reduce { it.copy(recipientName = owner) }
                    }

                    result.exceptionOrNull()?.let {
                        it.message?.let { message -> showToastUseCase(message) }
                    }
                }.launchIn(screenModelScope)
            }

            is OnLastRecipientSelected -> {
                recipientCardPan = intent.lastRecipient.pan
            }

            is OnCardToTransferFromSelected -> {
                uiState.reduce {
                    it.copy(
                        selectedCardIndex = intent.index
                    )
                }
            }

            is RecipientCardSelected -> {}
            OnBtnNextClick -> {
                screenModelScope.launch {
                    directions.navigateToConfirmScreen(
                        senderPan = uiState.value.userCardsList[uiState.value.selectedCardIndex].pan,
                        senderId = uiState.value.userCardsList[uiState.value.selectedCardIndex].id.toString(),
                        senderBalance = uiState.value.userCardsList[uiState.value.selectedCardIndex].amount.toString(),
                        receiverName = uiState.value.recipientName,
                        receiverPan = recipientCardPan,
                        amount = uiState.value.amount,
                        commission = uiState.value.commission
                    )
                }
            }

            OnBtnBackClick -> {
                screenModelScope.launch { directions.popBack() }
            }

            is OnAmountEntered -> {
                uiState.reduce { it.copy(amount = intent.amount) }
                val commission = calculateCommissionUseCase(
                    amount = intent.amount,
                )

                uiState.reduce { it.copy(commission = commission) }


            }

            OnAmountEmpty -> {
                uiState.reduce { it.copy(commission = 0.0) }
            }
        }

    }
}