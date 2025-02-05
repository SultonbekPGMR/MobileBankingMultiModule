package uz.gita.mobilebankingmultimodule.ui.screen.moneytransfer

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.common.util.formatBalance
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.components.card.AppCard
import uz.gita.mobilebankingmultimodule.ui.components.card.ReceiverCard
import uz.gita.mobilebankingmultimodule.ui.components.textfield.TextFieldCardNumber
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.grayDisabled
import uz.gita.mobilebankingmultimodule.ui.theme.green
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.ui.util.AmountMask
import uz.gita.presenter.contract.TransferMoneyScreenContract.*
import uz.gita.presenter.contract.TransferMoneyScreenContract.Intent.*
import uz.gita.presenter.viewmodel.TransferMoneyScreenViewModel


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */

class TransferMoneyScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel =
            getScreenModel<TransferMoneyScreenViewModel>()
        MoneyTransferScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            viewModel::onEventDispatcher
        )
    }

    @Preview
    @Composable
    private fun MoneyTransferScreenPreview() {
        MoneyTransferScreenContent()
    }

    @Composable
    private fun MoneyTransferScreenContent(
        uiState: State<UiState> = remember {
            mutableStateOf(
                UiState()
            )
        },
        eventDispatcher: (Intent) -> Unit = {},
    ) {

        val edtReceiverCard = remember { mutableStateOf("") }
        val edtAmount = remember { mutableStateOf("") }



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
                    text = stringResource(R.string.transfer_2card),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )


            }


            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = stringResource(R.string.lbl_card_receiver),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start
            )


            TextFieldCardNumber(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 4.dp),
                text = edtReceiverCard,
                onTextChange = {
                    Log.d("FKMFKMFK", "MoneyTransferScreenContent: $it")
                    val input = it
                    if (input.length <= 16) edtReceiverCard.value = input
                    eventDispatcher(
                        OnCardEntered(
                            edtReceiverCard.value
                        )
                    )
                }
            )
            if (uiState.value.recipientName.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = uiState.value.recipientName.uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = gray,
                    textAlign = TextAlign.Start
                )
            }
            if (uiState.value.recipientName.isNotEmpty()) {

                Card(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp, bottom = 8.dp),
                    border = BorderStroke(width = 2.dp, color = lightGreen),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardColors(
                        containerColor = Color.Transparent,
                        contentColor = gray,
                        disabledContentColor = gray,
                        disabledContainerColor = Color.Transparent,
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.write_amount),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = gray,
                                textAlign = TextAlign.Start
                            )
                            if (edtAmount.value.isEmpty() || edtAmount.value.toDouble() < 1000.00) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = stringResource(R.string.lbl_min_amount_1000),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Red,
                                    textAlign = TextAlign.End
                                )

                            }


                        }

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = green)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(R.string.uzs_title),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = gray,
                                    textAlign = TextAlign.End
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                if (edtAmount.value.isEmpty()) {
                                    Text(
                                        text = "0.00",
                                        fontSize = 18.sp,
                                        color = Color.Gray,
                                        modifier = Modifier.align(Alignment.CenterStart)
                                    )
                                }
                                BasicTextField(
                                    value = edtAmount.value,
                                    onValueChange = {

                                        if (it.isNotEmpty() && it.toDouble() > 100_000_000_000_000_000) return@BasicTextField

                                        edtAmount.value = it


                                        if (edtAmount.value.isNotEmpty() && edtAmount.value.toDouble() > 1000.00) {
                                            eventDispatcher(
                                                Intent.OnAmountEntered(
                                                    it.toDouble(),
                                                    edtReceiverCard.value
                                                )
                                            )
                                        } else {
                                            eventDispatcher(Intent.OnAmountEmpty)

                                        }
                                    },
                                    visualTransformation = AmountMask(maxFractionDigits = 2),
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth(),
                                    textStyle = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Decimal,
                                        imeAction = ImeAction.Done
                                    ),
                                )
                            }

                            Icon(
                                painter = painterResource(R.drawable.ic_rocket_selected),
                                contentDescription = null
                            )


                        }


                    }


                }

            } else {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    text = stringResource(R.string.lbl_last_recipients),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.value.lastRecipientsCardsList) {
                        ReceiverCard(
                            receiverData = it
                        ) {
                            edtReceiverCard.value = it.pan
                            eventDispatcher(OnCardEntered(it.pan))
                        }
                    }
                }

                OutlinedButton(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, lightGreen),
                    onClick = {}) {


                    Icon(
                        modifier = Modifier.padding(4.dp),
                        painter = painterResource(R.drawable.ic_rocket_selected),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = stringResource(R.string.lbl_all_recipients),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }


            if (uiState.value.commission > 0.0) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = stringResource(R.string.commission) + " " + formatBalance(uiState.value.commission),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = gray,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(
                modifier = Modifier.padding(8.dp)
            )

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = stringResource(R.string.choose_card),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(items = uiState.value.userCardsList) { index, cardData ->
                    AppCard(
                        cardData = cardData,
                        ownerNameVisibility = false,
                        isSelected = uiState.value.selectedCardIndex == index
                    ) {
                        eventDispatcher(OnCardToTransferFromSelected(index))
                    }

                }
            }




            Button(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 24.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightGreen,
                    disabledContainerColor = grayDisabled,
                    disabledContentColor = gray,
                    contentColor =
                    MaterialTheme.colorScheme.primary
                ),
                enabled =
                uiState.value.btnNextState
                        && uiState.value.recipientName.isNotEmpty()
                        && !(edtAmount.value.isEmpty() || edtAmount.value.toDouble() < 1000.00),
                onClick = {
                    eventDispatcher(Intent.OnBtnNextClick)
                }
            ) {
                Text(
                    text = stringResource(R.string.lbl_next),
                )
            }


        }

    }
}