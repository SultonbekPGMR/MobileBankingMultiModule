package uz.gita.mobilebankingmultimodule.ui.screen.confirmpayment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.grayDisabled
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.presenter.contract.ConfirmTransferScreenContract
import uz.gita.presenter.contract.ConfirmTransferScreenContract.*
import uz.gita.presenter.contract.ConfirmTransferScreenContract.Intent.OnBackClick
import uz.gita.presenter.contract.ConfirmTransferScreenContract.Intent.OnConfirmClick
import uz.gita.presenter.viewmodel.ConfirmTransferViewModel

/**
 * Created by Sultonbek Tulanov on 1/10/2025.
 */

class ConfirmTransferScreen(private val transferData: TransferData) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ConfirmTransferScreenContract.ViewModel =
            getScreenModel<ConfirmTransferViewModel>()
        ConfirmPaymentScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }

    constructor() : this(TransferData())

    @Preview
    @Composable
    private fun ConfirmPaymentScreenPreview() {
        ConfirmPaymentScreenContent()
    }

    @Composable
    private fun ConfirmPaymentScreenContent(
        uiState: State<UiState> = mutableStateOf(UiState()),
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
                        eventDispatcher(OnBackClick)
                    },
                    imageVector = Filled.ArrowBackIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.confirmation_of_payment),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )


            }

            Card(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {


                    Text(
                        text = transferData.senderBalance,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = transferData.senderPan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = gray,
                        textAlign = TextAlign.Center
                    )


                }

            }


            Card(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
                    .fillMaxWidth().clip(
                        shape = RoundedCornerShape(12.dp)
                    ).background(color = MaterialTheme.colorScheme.tertiaryContainer),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.tertiary),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                )

            ) {
             Column(
                 modifier = Modifier.padding(20.dp)
             ) {
                 Row(
                     modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()
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
                     modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
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
                     modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()
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
                     modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
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
             }

            }

            Card(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 4.dp)
                    .fillMaxWidth().clip(
                        shape = RoundedCornerShape(12.dp)
                    ).background(color = MaterialTheme.colorScheme.tertiaryContainer),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.tertiary),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                )

            ) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 32.dp)
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
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.End
                    )


                }

            }


            Spacer(
                modifier = Modifier.weight(1f)
            )


            Button(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 24.dp)
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
                enabled = uiState.value.btnConfirmState,
                onClick = {
                    eventDispatcher(
                        OnConfirmClick(
                        transferData = transferData
                    )
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.lbl_confirm),
                )
            }


        }
    }
}
