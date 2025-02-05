package uz.gita.mobilebankingmultimodule.ui.screen.auth.verify

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.common.model.VerifyType
import uz.gita.common.model.VerifyType.Auth
import uz.gita.common.model.VerifyType.Transfer
import uz.gita.common.util.formatSecondsToTime
import uz.gita.mobilebankingauthcompose.ui.screen.auth.verify.VerifyContract.*
import uz.gita.mobilebankingauthcompose.ui.screen.auth.verify.VerifyContract.Intent.PopBack
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.backgroundGradient
import uz.gita.mobilebankingmultimodule.ui.theme.inactiveBackground
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.ui.components.BtnBack
import uz.gita.presenter.viewmodel.VerifyViewModel


/**
 * Created by Sultonbek Tulanov on 25/12/2024.
 */

class VerifyScreen(
    private val phoneNumber: String = "",
    private val type: VerifyType = Auth(),
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel =
            getScreenModel<VerifyViewModel, VerifyViewModel.Factory> {
                it.create(verifyType = type)
            }
        VerifyScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }
//
//    constructor() : this()
//
//    @Preview
//    @Composable
//    private fun VerifyScreenPreview() {
//        VerifyScreenContent()
//    }


    @Composable
    private fun VerifyScreenContent(
        uiState: State<UiState> = remember { mutableStateOf(UiState()) },
        eventDispatcher: (Intent) -> Unit = {},
    ) {

        val otpText = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(backgroundGradient)
                .padding(top = 24.dp, bottom = 24.dp)
        ) {

            BtnBack {
                eventDispatcher.invoke(PopBack)
            }

            Text(
                text = when (type) {
                    is Auth -> stringResource(R.string.verify_account)
                    is Transfer -> stringResource(R.string.verify_code)
                },
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,

                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp, start = 20.dp, end = 20.dp),

                )
            if (type is Auth) {
                Text(
                    text = stringResource(R.string.verify_code),
                    fontSize = 13.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.6f)
                        .padding(top = 8.dp),
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0x05F0F5F0)),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color(0x1FE1E5E1)),
                    onClick = {
                        eventDispatcher(PopBack)
                    }

                ) {
                    Text(
                        text = phoneNumber,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = null
                    )


                }

            }



            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                OtpTextField(otpText) {
                    if (it.length <= 6) otpText.value = it
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        disabledContentColor = inactiveBackground,
                    ),
                    border = BorderStroke(
                        1.dp,
                        color = if (uiState.value.resendTimerState == 0) lightGreen else inactiveBackground
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = uiState.value.resendTimerState == 0,
                    onClick = {
                        eventDispatcher(Intent.ResendCode)
                    }

                ) {


                    Image(
                        painter = painterResource(R.drawable.ic_refresh),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(R.string.send_again),
                        fontSize = 14.sp,
                        color = Color.White
                    )

                }

                if (uiState.value.resendTimerState > 0) {


                    Log.d("TTTTEEE", "VerifyScreenContent: ${uiState.value.resendTimerState}")

                    Box(
                        modifier = Modifier
                            .border(
                                border = BorderStroke(1.dp, inactiveBackground),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(start = 8.dp, end = 8.dp, top = 6.dp, bottom = 6.dp)
                    ) {

                        Text(
                            text = formatSecondsToTime(uiState.value.resendTimerState),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }

            Spacer(
                modifier = Modifier.weight(1f)
            )
            Button(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 24.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightGreen),
                onClick = {
                    eventDispatcher.invoke(Intent.CheckCode(phoneNumber, otpText.value))
                },
                enabled = uiState.value.btnConfirmState && otpText.value.length == 6,

                ) {
                Text(text = stringResource(R.string.confirm), color = Color.White)

            }


        }


    }


    @Composable
    fun OtpTextField(
        otpText: State<String>,
        onValueChange: (String) -> Unit,
    ) {

        val focusedState =
            remember { mutableStateOf(mutableListOf(false, false, false, false, false, false)) }

        BasicTextField(
            value = otpText.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            onValueChange = {
                onValueChange(it)
            },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                repeat(6) { index ->
                    val number = when {
                        index >= otpText.value.length -> {
                            focusedState.value = focusedState.value.apply {
                                this[index] = false
                            }.toMutableList()
                            ""
                        }

                        else -> {
                            focusedState.value = focusedState.value.apply {
                                this[index] = true
                            }.toMutableList()
                            otpText.value[index]
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,

                            modifier = Modifier
                                .width(40.dp)
                                .height(56.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (focusedState.value[index]) lightGreen else inactiveBackground,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clip(
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .background(color = inactiveBackground)
                        ) {
                            Text(
                                text = number.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )

                        }


                    }

                }


            }

        }


    }

}