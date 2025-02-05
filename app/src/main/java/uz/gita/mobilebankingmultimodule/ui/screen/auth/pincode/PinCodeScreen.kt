package uz.gita.mobilebankingmultimodule.ui.screen.auth.pincode

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import uz.gita.mobilebankingmultimodule.ui.theme.*
import uz.gita.presenter.contract.PinCodeContract
import uz.gita.presenter.contract.PinCodeContract.*
import uz.gita.presenter.contract.PinCodeContract.Intent.OnBiometricsClick
import uz.gita.presenter.viewmodel.PinCodeViewModel


/**
 * Created by Sultonbek Tulanov on 25/12/2024.
 */


class PinCodeScreen(private val newPinCode: Boolean = false) : Screen {
    constructor() : this(false)

    @Composable
    override fun Content() {
        val viewModel: PinCodeContract.ViewModel =
            getScreenModel<PinCodeViewModel, PinCodeViewModel.Factory> { it.create(newPinCode) }
        PinCodeScreenContents(
            uiState = viewModel.uiState.collectAsState(),
            viewModel::onEventDispatcher
        )
    }


    @[Preview Composable]
    private fun PinCodeScreenPreview() {
        PinCodeScreenContents()
    }

    @Composable
    private fun PinCodeScreenContents(
        uiState: State<UiState> = remember { mutableStateOf(UiState()) },
        eventDispatcher: (Intent) -> Unit = {},
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(backgroundGradient)
                .padding(top = 24.dp, bottom = 24.dp)
        ) {

//            BtnBack {
//                navigator.pop()
//            }

            Spacer(
                modifier = Modifier.weight(0.2f)
            )


            Text(

                text = stringResource(
                    id = if(newPinCode) R.string.think_of_pin_code else R.string.lbl_enter_current_pin
                ),
                fontSize = 24.sp,
                color = Color.White,

                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp, bottom = 8.dp),

                )


            Text(
                text = stringResource(R.string.lbl_pin_code_no_equals),
                fontSize = 14.sp,
                color = Color.Red,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(alpha = if (uiState.value.txtErrorState) 1f else 0f)
                    .padding(top = 4.dp, bottom = 24.dp),
            )



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    repeat(4) {
                        Box(
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp)
                                .background(
                                    color = if (uiState.value.typedNumCount > it) {
                                        if (uiState.value.txtErrorState) Color.Red else lightGreen
                                    } else green,
                                    shape = CircleShape
                                )
                                .border(width = 1.dp, color = green, shape = CircleShape),
                        )

                    }
                }

            }
            if (newPinCode) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(vertical = 4.dp, horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = stringResource(R.string.use_face_id),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = black2
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Switch(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .border(
                                width = 0.dp, // No border stroke
                                color = Color.Transparent, // Transparent border when unchecked
                            ),
                        checked = uiState.value.biometricsState,
                        onCheckedChange = { eventDispatcher(OnBiometricsClick(it)) },
                        thumbContent = { },
                        enabled = true,

                        colors = SwitchDefaults.colors(
                            checkedTrackColor = lightGreen,
                            uncheckedTrackColor = whiteLighter,
                            checkedThumbColor = Color.White,
                            uncheckedThumbColor = Color.White
                        )
                    )


                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 70.dp, bottom = 50.dp)
            ) {
                repeat(4) { i ->
                    Row(modifier = Modifier.weight(1f)) {
                        repeat(3) { j ->
                            val txt = when {
                                i != 3 -> (i * 3 + j + 1).toString()

                                i == 3 -> {
                                    when (j) {
                                        0 -> ""
                                        1 -> "0"
                                        else -> "777"
                                    }
                                }

                                else -> {
                                    ""
                                }
                            }

                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(CircleShape)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = ripple(
                                            radius = 100.dp,
                                            color = lightGreen
                                        ),
                                    ) {},
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,

                                    ),
                                enabled = uiState.value.buttonsClickableState,
                                onClick = {
                                    eventDispatcher(
                                        if (txt == "777") Intent.OnBackSpaceClick else Intent.OnNumberClick(
                                            txt.toInt()
                                        )
                                    )
                                },

                                ) {

                                if (txt == "777") {
                                    Image(
                                        painter = painterResource(R.drawable.ic_outline_backspace),
                                        contentDescription = null
                                    )
                                } else {
                                    Text(
                                        text = txt,
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 28.sp
                                    )
                                }

                            }
                        }
                    }

                }
            }

        }
    }

}