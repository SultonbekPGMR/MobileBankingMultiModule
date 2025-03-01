package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uz.gita.common.model.MainServiceData
import uz.gita.common.util.reduce
import uz.gita.presenter.R
import uz.gita.presenter.contract.HomeScreenContract.*
import uz.gita.presenter.contract.HomeScreenContract.Intent.*
import uz.gita.usecase.usecase.GetAllCardsUseCase
import uz.gita.usecase.usecase.GetTotalBalanceUseCase
import uz.gita.usecase.usecase.GetUserInfoUseCase
import uz.gita.usecase.usecase.ShowToastUseCase
import javax.inject.Inject

/**
 * Created by Sultonbek Tulanov on 02/01/2025.
 */

class HomeViewModel @Inject constructor(
    private val showToastUseCase: ShowToastUseCase,
    private val directions: Directions,
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : ViewModel, ScreenModel {
    override val uiState = MutableStateFlow(UiState())

    init {

        loadData()
    }

    private fun loadData() {

        screenModelScope.launch {
            uiState.reduce { it.copy(isRefreshing = true) }
            getUserInfoUseCase()?.let { userData ->
                uiState.reduce { it.copy(userName = userData.firstName.uppercase()) }
            }

            val userCards = async { getAllCardsUseCase().first().getOrNull() ?: emptyList() }
            val totalBalance = async { getTotalBalanceUseCase().first() }

            uiState.reduce {
                it.copy(
                    userCardsList = userCards.await(),
                    totalBalance = totalBalance.await(),
                )
            }
            uiState.reduce { it.copy(isRefreshing = false) }

        }

    }


    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is OnServiceClicked -> {
                handleServiceClick(intent.service)

            }

            is OnCardClick -> {
                screenModelScope.launch {
                    directions.navigateToCardDetailsScreen(intent.cardData)
                }
            }

            is OnNotificationClick -> {}
            is OnSupportClick -> {}
            OnAddCardClick -> {
                screenModelScope.launch {
                    directions.navigateToAddCardScreen()
                }
            }

            OnAllCardsClick -> {}
            OnBalanceEyeClick -> {}
            OnSearchClick -> {}
            OnTotalBalanceClick -> {}
            OnProfileClick -> {
                screenModelScope.launch {
                    directions.navigateToUserDetailsScreen()
                }
            }

            OnRefresh -> {
                loadData()
            }
        }
    }

    private fun handleServiceClick(service: MainServiceData) {
        screenModelScope.launch {
            when (service.nameId) {
                R.string.lbl_my_pension -> {
                    showToastUseCase("Coming soon")
                }

                R.string.lbl_inps -> {
                    showToastUseCase("Coming soon")

                }

                R.string.lbl_my_bank -> {
                    showToastUseCase("Coming soon")

                }

                R.string.lbl_transfers -> {
                    directions.navigateToMoneyTransferScreen()
                }

                R.string.lbl_my_home -> {
                    showToastUseCase("Coming soon")

                }

                R.string.my_number -> {
                    showToastUseCase("Coming soon")
                }

                else -> {
                    showToastUseCase("Coming soon")
                }
            }
        }
    }

}