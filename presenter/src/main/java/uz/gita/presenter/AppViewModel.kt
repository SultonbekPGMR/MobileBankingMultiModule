package uz.gita.presenter

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

interface AppViewModel<STATE : Any, SIDE_EFFECT : Any> : ContainerHost<STATE, SIDE_EFFECT>,
    ScreenModel {
    fun <STATE : Any, SIDE_EFFECT : Any> container(
        initialState: STATE,
        onCreate: ((state: STATE) -> Unit)? = null,
    ) = screenModelScope.container<STATE, SIDE_EFFECT>(initialState)
}