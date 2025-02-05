package uz.gita.mobilebankingmultimodule.ui.screen.transfercomplete

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.common.model.TransferData
import uz.gita.common.util.formatBalance
import uz.gita.mobilebankingauthcompose.presenter.viewmodel.TransferCompletedViewModel
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.grayDisabled
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.ui.components.card.ExpandableCard
import uz.gita.presenter.contract.TransferCompletedContract
import uz.gita.presenter.contract.TransferCompletedContract.*
import uz.gita.presenter.contract.TransferCompletedContract.Intent.OnNavigateToHomeClick

/**
 * Created by Sultonbek Tulanov on 1/11/2025.
 */

class TransferCompletedScreen(private val transferData: TransferData) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel =
            getScreenModel<TransferCompletedViewModel>()
        TransferCompletedScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }

    constructor() : this(TransferData())

    @Preview
    @Composable
    private fun TransferCompletedScreenPreview() {
        TransferCompletedScreenContent()
    }

    @Composable
    private fun TransferCompletedScreenContent(
        uiState: State<UiState> = mutableStateOf(UiState()),
        eventDispatcher: (Intent) -> Unit = {},
    ) {

        BackHandler {
            eventDispatcher(OnNavigateToHomeClick)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Icon(
                modifier = Modifier.padding( top = 24.dp)
                    .padding(20.dp),
                tint = lightGreen,
                painter = painterResource(R.drawable.ic_rocket_selected),
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(28.dp),
                    painter = painterResource(R.drawable.ic_rocket_selected),
                    tint = lightGreen,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = transferData.amount,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    Text(
                        text = stringResource(R.string.lbl_success_humo_pay_result),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = gray,
                        textAlign = TextAlign.Center
                    )
                }

            }

            ExpandableCard(
                title = stringResource(R.string.lbl_payment_details),
                titleTextSize = 17.sp,
            ) {

                Column(
                    modifier = Modifier.padding(start = 36.dp, end =  16.dp, bottom = 16.dp,)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.lbl_date),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Center
                        )


                        Text(
                            modifier = Modifier.weight(1f),
                            text = transferData.date,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )


                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.5.dp, color = gray
                    )
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.receiver_name),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Center
                        )


                        Text(
                            modifier = Modifier.weight(1f),
                            text = transferData.receiverName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )


                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.receiver),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Center
                        )


                        Text(
                            modifier = Modifier.weight(1f),
                            text = transferData.receiverPan,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )


                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.5.dp, color = gray
                    )
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.lbl_amount),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Center
                        )


                        Text(
                            modifier = Modifier.weight(1f),
                            text = transferData.amount,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )


                    }
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.commission),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Center
                        )


                        Text(
                            modifier = Modifier.weight(1f),
                            text = transferData.commission,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )


                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.lbl_sender),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Center
                        )


                        Text(
                            modifier = Modifier.weight(1f),
                            text = transferData.senderPan,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )


                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.5.dp, color = gray
                    )

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.total),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = gray,
                            textAlign = TextAlign.Center
                        )


                        Text(
                            modifier = Modifier.weight(1f),
                            text = formatBalance(transferData.total),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )


                    }

                }

            }

            Spacer(
                modifier = Modifier.weight(1.2f)
            )

            Button(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .padding(bottom = 36.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightGreen,
                    disabledContainerColor = grayDisabled,
                    disabledContentColor = gray,
                    contentColor =
                    MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    eventDispatcher(
                        OnNavigateToHomeClick
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.lbl_return_home),
                )
            }

        }

    }
}
