package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gita.common.model.MainServiceData
import uz.gita.presenter.R
import uz.gita.presenter.contract.TransfersContract.*
import uz.gita.presenter.contract.TransfersContract.Intent.OnOptionSelected
import javax.inject.Inject
import java.time.LocalDate


/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

class TransfersViewModel @Inject constructor(
    private val directions: Directions,
) : ViewModel, ScreenModel {


    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is OnOptionSelected -> {
                handleOptionClick(intent.mainServiceData)
            }
        }
    }

    private fun handleOptionClick(data: MainServiceData) {
        when (data.nameId) {
            R.string.transfer_to_card -> {
                screenModelScope.launch { directions.navigateToMoneyTransferScreen() }
            }
            R.string.qr_share -> {}
            R.string.lbl_transfer_wallets -> {}
            R.string.transfer_between_my_cards -> {}
            R.string.transfer_by_requisites -> {}
            R.string.transfer_conversion -> {}
            R.string.international_transfers -> {}
            R.string.lbl_ask_money -> {}
            R.string.asking_for_a_loan -> {}
            else -> {}
        }
    }

}
