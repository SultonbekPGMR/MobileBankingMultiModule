package uz.gita.presenter.viewmodel

import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.common.util.reduce
import uz.gita.presenter.contract.MonitoringScreenContract.*
import uz.gita.presenter.contract.MonitoringScreenContract.Intent.*
import uz.gita.usecase.usecase.CalculateHistoryUseCase
import uz.gita.usecase.usecase.GetHistoryUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 1/12/2025.
 */

class MonitoringScreenViewModel @Inject constructor(
    private val directions: Directions,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val calculateHistoryUseCase: CalculateHistoryUseCase,
) : ViewModel, ScreenModel {
    private val transactionItems = getHistoryUseCase().cachedIn(screenModelScope)
    override val uiState = MutableStateFlow(
        UiState(
            transactionItems = transactionItems,
        )
    )

    init {
        loadData()
    }

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            OnBtnExpenseClick -> {

                loadData(expense = !uiState.value.btnExpense)
            }

            OnBtnIncomeClick -> {
                loadData(income = !uiState.value.btnIncome)
            }

            is OnTabSelected -> {
                uiState.reduce { it.copy(selectedTabIndex = intent.index) }
            }
        }
    }

    private fun loadData(
        income: Boolean = false,
        expense: Boolean = false,
    ) {
        if (income || expense) uiState.reduce { it.copy(selectedTabIndex = 0) }
        uiState.reduce { it.copy(btnIncome = income, btnExpense = expense) }


        screenModelScope.launch {
            val income = calculateHistoryUseCase(
                transactions = transactionItems,
                calculateIncome = income,
            )

            val expense = calculateHistoryUseCase(
                transactions = transactionItems,
                calculateIncome = expense
            )

            uiState.reduce {
                it.copy(
                    income = income,
                    expense = expense,

                    )
            }


        }


    }
}

//uiState.reduce {
//                it.copy(
//                    income = calculateHistoryUseCase(transactions, true),
//                    expense = calculateHistoryUseCase(transactions, false),
//                    transactionsList = getHistoryUseCase(
//                        income = income,
//                        expense = expense
//                    )
//                )
//            }