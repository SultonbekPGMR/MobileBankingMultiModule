package uz.gita.mobilebankingmultimodule.ui.screen.transfer

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.presenter.contract.TransfersContract
import uz.gita.presenter.contract.TransfersContract.Intent.OnOptionSelected
import uz.gita.presenter.viewmodel.TransfersViewModel

/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

class TransfersScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: TransfersContract.ViewModel = getScreenModel<TransfersViewModel>()
        TransfersScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }

    @Preview
    @Composable
    private fun TransfersScreenPreview() {
        TransfersScreenContent()
    }

    @Composable
    private fun TransfersScreenContent(
        uiState: State<TransfersContract.UiState> = mutableStateOf(TransfersContract.UiState()),
        eventDispatcher: (TransfersContract.Intent) -> Unit = {},
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(top = 36.dp)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = stringResource(R.string.transfer_2card),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )


            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(uiState.value.options) { data ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = LocalIndication.current
                            )  {
                                eventDispatcher(
                                    OnOptionSelected(
                                        data
                                    )
                                )
                            }
                            .shadow(
                                elevation = 0.5.dp,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        shape = RoundedCornerShape(8.dp),

                        ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            .padding(top = 10.dp, bottom = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Icon(
                                modifier = Modifier.padding(4.dp),
                                painter = painterResource(data.iconId),
                                contentDescription = data.iconId.toString(),
                                tint = lightGreen
                            )
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = stringResource(data.nameId),
                                fontSize = 14.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

        }

    }
}
