package uz.gita.mobilebankingauthcompose.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.mobilebankingmultimodule.ui.screen.home.HomeScreen
import uz.gita.mobilebankingauthcompose.ui.screen.menu.MenuScreen
import uz.gita.mobilebankingmultimodule.ui.screen.monitoring.MonitoringScreen
import uz.gita.mobilebankingmultimodule.ui.screen.payment.PaymentsScreen
import uz.gita.mobilebankingmultimodule.ui.screen.transfer.TransfersScreen
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.presenter.contract.MainScreenContract.*
import uz.gita.presenter.contract.MainScreenContract.Intent.OnBottomNavClick
import uz.gita.presenter.viewmodel.MainScreenViewModel


/**
 * Created by Sultonbek Tulanov on 01/01/2025.
 */

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getScreenModel<MainScreenViewModel>()
        MainScreenContent(
            viewModel.uiState.collectAsState(),
            viewModel::onEventDispatcher
        )
    }

    @Preview
    @Composable
    private fun MainScreenPreview() {
        MainScreenContent()
    }

    @Composable
    fun MainScreenContent(
        uiState: State<UiState> = remember { mutableStateOf(UiState()) },
        eventDispatcher: (Intent) -> Unit = {},
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1f)) {
                when (uiState.value.currentScreen) {
                    BottomNavItem.Home -> HomeScreen().Content()
                    BottomNavItem.Transfers -> TransfersScreen().Content()
                    BottomNavItem.Payments -> PaymentsScreen().Content()
                    BottomNavItem.Monitoring -> MonitoringScreen().Content()
                    BottomNavItem.Menu -> MenuScreen().Content()
                }
            }

                BottomNavigationBar(
                    currentScreen = uiState.value.currentScreen,
                    onNavItemClicked = { eventDispatcher(OnBottomNavClick(it)) }
                )
        }


    }

    @Composable
    fun BottomNavigationBar(
        currentScreen: BottomNavItem,
        onNavItemClicked: (BottomNavItem) -> Unit,
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            tonalElevation = 16.dp,
            modifier = Modifier
                .height(70.dp)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(vertical = 8.dp)
        ) {
            BottomNavItem.entries.forEach { item ->
                NavigationBarItem(

                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surfaceContainer)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = ripple(
                                bounded = true,
                                color = lightGreen
                            ),
                            onClick = { onNavItemClicked(item) }
                        ),

                    icon = {
                        Icon(
                            modifier = Modifier.size(22.dp),
                            painter = painterResource(
                                if (currentScreen == item) item.selectedItemIconId else item.iconId ),
                            contentDescription = stringResource(item.labelId),
                        )

                    },
                    label = {
                        Text(
                            text = stringResource(item.labelId),
                            fontSize = 12.sp,
                            maxLines = 1,
                            fontWeight = FontWeight.Light
                        )
                    },
                    selected = currentScreen == item,
                    onClick = { onNavItemClicked(item) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = lightGreen,
                        selectedTextColor = lightGreen,
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        disabledIconColor = Color.Black,
                        disabledTextColor = Color.Black
                    )
                )
            }
        }
    }


}