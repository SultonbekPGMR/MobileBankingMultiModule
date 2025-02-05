package uz.gita.presenter.contract

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.common.model.TransactionData
import uz.gita.presenter.contract.MonitoringScreenContract.Tab.All
import uz.gita.presenter.contract.MonitoringScreenContract.Tab.ByCard
import uz.gita.presenter.R

/**
 * Created by Sultonbek Tulanov on 1/12/2025.
 */

interface MonitoringScreenContract {


    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)

    }

    data class UiState(
        val selectedTabIndex: Int = 0,
        val btnIncome: Boolean = false,
        val btnExpense: Boolean = false,
        val income: Double = 0.00,
        val expense: Double = 0.00,
        val tabs: List<Tab> = listOf(All, ByCard),
        val transactionItems: Flow<PagingData<TransactionData>>  = MutableSharedFlow(),
    )


    sealed class Tab(val titleId: Int) {
        data object All : Tab(R.string.all)
        data object ByCard : Tab(R.string.with_card)
    }

    sealed interface Intent {
        data object OnBtnIncomeClick : Intent
        data object OnBtnExpenseClick : Intent
        data class OnTabSelected(val index:Int) : Intent
    }

    interface Directions {
        suspend fun popBack()
    }
}
