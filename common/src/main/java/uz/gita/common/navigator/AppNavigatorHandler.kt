package uz.gita.common.navigator

import kotlinx.coroutines.flow.Flow

interface AppNavigatorHandler {
    val navigation: Flow<NavigatorArgs>
     val toastStack : Flow<String>

}