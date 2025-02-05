package uz.gita.presenter.viewmodel

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.common.util.reduce
import uz.gita.presenter.contract.AddCardContract.*
import uz.gita.presenter.contract.AddCardContract.Intent.OnBtnAddCardClicked
import uz.gita.presenter.contract.AddCardContract.Intent.OnBtnBackClick
import uz.gita.usecase.usecase.AddCardUseCase
import uz.gita.usecase.usecase.ShowToastUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 1/8/2025.
 */

class AddCardViewModel @Inject constructor(
    private val directions: Directions,
    private val addCardUseCase: AddCardUseCase,
    private val showToastUseCase: ShowToastUseCase,
) : ViewModel, ScreenModel {


    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatcher(intent: Intent) {
        screenModelScope.launch {
            when (intent) {
                OnBtnBackClick -> {
                    directions.popBack()
                }

                is OnBtnAddCardClicked -> {
                    val expiredMonth = intent.expiredDate.substring(0, 2)
                    if (expiredMonth.toInt() > 12 || expiredMonth.toInt() == 0) {
                        showToastUseCase("expire date is invalid!")
                        return@launch
                    }
                    val expiredYear = "20"+intent.expiredDate.substring(2, 4)

                    addCardUseCase(
                        pan = intent.pan,
                        expiredMonth = expiredMonth,
                        expiredYear = expiredYear,
                        name = intent.name,
                    ).onStart {
                        uiState.reduce { it.copy(btnAddState = false) }

                    }.onCompletion {
                        uiState.reduce { it.copy(btnAddState = true) }

                    }.onEach { result ->
                        if (result.isSuccess) {
                            showToastUseCase("Success")
                            directions.popBack()
                        } else {
                            Log.d(
                                "YUIOIUYTYU",
                                "onEventDispatcher: ${result.exceptionOrNull()?.message}"
                            )
                            showToastUseCase(result.exceptionOrNull()?.message ?: "card not found")
                        }

                    }.launchIn(screenModelScope)
                }
            }
        }

    }
}
