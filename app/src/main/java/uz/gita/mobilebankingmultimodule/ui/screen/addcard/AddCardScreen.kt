package uz.gita.mobilebankingmultimodule.ui.screen.addcard

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.grayDisabled
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.ui.theme.whiteVeryLight
import uz.gita.mobilebankingmultimodule.ui.components.textfield.AppTextField
import uz.gita.mobilebankingmultimodule.ui.components.textfield.TextFieldCardExpireDate
import uz.gita.mobilebankingmultimodule.ui.components.textfield.TextFieldCardNumberWithoutLeadingIcon
import uz.gita.presenter.contract.AddCardContract
import uz.gita.presenter.contract.AddCardContract.Intent
import uz.gita.presenter.contract.AddCardContract.Intent.OnBtnAddCardClicked
import uz.gita.presenter.viewmodel.AddCardViewModel

/**
 * Created by Sultonbek Tulanov on 1/8/2025.
 */

class AddCardScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AddCardContract.ViewModel = getScreenModel<AddCardViewModel>()
        AddCardScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }

    @Preview
    @Composable
    private fun AddCardScreenPreview() {
        AddCardScreenContent()
    }

    @Composable
    private fun AddCardScreenContent(
        uiState: State<AddCardContract.UiState> = mutableStateOf(AddCardContract.UiState()),
        eventDispatcher: (AddCardContract.Intent) -> Unit = {},
    ) {

        val edtCardNumber = remember { mutableStateOf("") }
        val edtCardExpireDate = remember { mutableStateOf("") }
        val edtCardName = remember { mutableStateOf("") }

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
                    text = stringResource(R.string.add_card),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )


            }

            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(gray),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Image(
                        modifier = Modifier.matchParentSize(),
                        painter = painterResource(R.drawable.bg_card_1),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = stringResource(R.string.lbl_card_number),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )

                        TextFieldCardNumberWithoutLeadingIcon(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            text = edtCardNumber,
                            containerColor = whiteVeryLight,
                        ) {

                            if (it.length <= 16) edtCardNumber.value = it
                        }

                        Text(
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                            text = stringResource(R.string.lbl_card_expire),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )



                        TextFieldCardExpireDate(
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp),
                            text = edtCardExpireDate,
                            containerColor = whiteVeryLight,
                            leadingIconVisibility = false,
                        ) {
                            if (it.length < 5) edtCardExpireDate.value = it
                        }


                    }

                }


            }

            Log.d("TTTTOOO", "AddCardScreenContent: ${edtCardName.value}")
            Log.d("TTTTOOO", "AddCardScreenContent: ${edtCardNumber.value}")
            Log.d("TTTTOOO", "AddCardScreenContent: ${edtCardExpireDate.value}")

            AppTextField(
                label = stringResource(R.string.lbl_card_name),
                placeHolder = stringResource(R.string.lbl_enter_card_name),
                text = { edtCardName.value },
                onTextChange = { if (it.length <= 20) edtCardName.value = it.replace(" ","") },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            )


            Spacer(
                modifier = Modifier.weight(1f)
            )

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
                    Color.White
                ),
                enabled =
                edtCardName.value.isNotEmpty()
                        && edtCardNumber.value.length == 16
                        && edtCardExpireDate.value.length == 4 && uiState.value.btnAddState,
                onClick = {
                    eventDispatcher(
                        OnBtnAddCardClicked(
                            pan = edtCardNumber.value,
                            expiredDate = edtCardExpireDate.value,
                            name = edtCardName.value
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.lbl_add),
                )
            }


        }


    }
}
