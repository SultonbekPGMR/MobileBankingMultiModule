package uz.gita.mobilebankingmultimodule.ui.screen.userdetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract.*
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.screen.userdetails.dialog.LogOutDialog
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.presenter.viewmodel.UserDetailsScreenViewModel


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

class UserDetailsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel =
            getScreenModel<UserDetailsScreenViewModel>()

        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        UserDetailsScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )

        LaunchedEffect(Unit) {

            viewModel.sideEffectFlow.onEach {
                val dialog = LogOutDialog().apply {
                    onConfirm = {
                        bottomSheetNavigator.hide()
                        viewModel.onEventDispatcher(Intent.LogOutUser)
                    }
                    onDismiss = { bottomSheetNavigator.hide() }
                }

                bottomSheetNavigator.show(dialog)


            }.launchIn(this)


        }


    }

    @Preview
    @Composable
    private fun UserDetailsScreenPreview() {
        UserDetailsScreenContent()
    }

    @Composable
    private fun UserDetailsScreenContent(
        uiState: State<UiState> = remember {
            mutableStateOf(
                UiState()
            )
        },

        eventDispatcher: (Intent) -> Unit = {},
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(top = 24.dp)


        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier.clickable {
                        eventDispatcher(Intent.OnBtnBackClick)
                    },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.xazna_user),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

            }


            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(color = Color.Transparent)
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(R.drawable.ic_rocket_selected),
                    contentDescription = null,
                    tint = lightGreen
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {

                    Text(
                        modifier = Modifier.padding(),
                        text = uiState.value.userName.uppercase(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(13.dp),
                            painter = painterResource(R.drawable.ic_transfer_selected),
                            contentDescription = null,
                            tint = lightGreen
                        )

                        Text(
                            text = stringResource(R.string.xazna_user),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Start
                        )
                    }

                }

            }


            repeat(uiState.value.availableOptions.size) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(color = MaterialTheme.colorScheme.outlineVariant)
                        .clickable {
                            eventDispatcher(Intent.OnOptionSelected(uiState.value.availableOptions[it]))
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        painter = painterResource(uiState.value.availableOptions[it].iconId),
                        contentDescription = null,
                        tint = gray
                    )
                    Text(
                        text = stringResource(uiState.value.availableOptions[it].titleId),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = TextUnit(0.2f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }


}