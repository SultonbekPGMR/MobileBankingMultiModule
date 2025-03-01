package uz.gita.presenter.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.hilt.ScreenModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import uz.gita.common.model.CardData
import uz.gita.presenter.contract.CardDetailsContract.*
import uz.gita.presenter.contract.CardDetailsContract.Intent.OnBackClick


/**
 * Created by Sultonbek Tulanov on 3/1/2025.
 */

class CardDetailsViewModel @AssistedInject constructor(
    @Assisted private val cardData: CardData,
    private val directions: Directions,
) : ViewModel, ScreenModel {

    @AssistedFactory
    interface Factory : ScreenModelFactory {
        fun create(cardData: CardData): CardDetailsViewModel
    }

    override val container: Container<UiState, SideEffect> = container(UiState(cardData))


    override fun onEventDispatcher(intent: Intent) = intent {
        when (intent) {
            OnBackClick -> {
                screenModelScope.launch {
                    directions.popBack()
                }
            }
        }
    }

}
