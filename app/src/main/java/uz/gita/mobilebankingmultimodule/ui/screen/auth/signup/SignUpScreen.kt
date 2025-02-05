package uz.gita.mobilebankingmultimodule.ui.screen.auth.signup

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import uz.gita.common.util.checkNumber
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.components.BtnBack
import uz.gita.mobilebankingmultimodule.ui.components.CustomTextField
import uz.gita.mobilebankingmultimodule.ui.theme.backgroundGradient
import uz.gita.mobilebankingmultimodule.ui.theme.inactiveBackground
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.presenter.contract.SignUpContract
import uz.gita.presenter.contract.SignUpContract.*
import uz.gita.presenter.contract.SignUpContract.Intent.PopBack
import uz.gita.presenter.viewmodel.SignUpViewModel


/**
 * Created by Sultonbek Tulanov on 25/12/2024.
 */

class SignUpScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: SignUpContract.ViewModel = getScreenModel<SignUpViewModel>()
        SignUpScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }


    @Composable
    @Preview
    private fun SignUpScreenPreview() {
        SignUpScreenContent()
    }

    @Composable
    private fun SignUpScreenContent(
        uiState: State<SignUpContract.UiState> = remember { mutableStateOf(SignUpContract.UiState()) },
        eventDispatcher: (SignUpContract.Intent) -> Unit = {},
    ) {


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
                text = stringResource(R.string.registration),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 80.dp),

                )

            var edtPhoneNumber by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.phone_number),
                placeHolder = stringResource(R.string.phone_number),
                text = { edtPhoneNumber },
                onTextChange = { if (it.length <= 13) edtPhoneNumber = it },
                keyboardType = KeyboardType.Phone
            )

            var edtFirstName by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.enter_name),
                placeHolder = stringResource(R.string.enter_name),
                text = { edtFirstName },
                onTextChange = { if (it.length <= 30) edtFirstName = it },
                keyboardType = KeyboardType.Text
            )

            var edtLastName by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.lbl_surname),
                placeHolder = stringResource(R.string.enter_lastname),
                text = { edtLastName },
                onTextChange = { if (it.length <= 30) edtLastName = it },
                keyboardType = KeyboardType.Text
            )
            var edtAge by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.lbl_age),
                placeHolder = stringResource(R.string.enter_age),
                text = { edtAge },
                onTextChange = { edtAge = it },
                keyboardType = KeyboardType.Decimal
            )
            var edtGender by rememberSaveable { mutableIntStateOf(0) }
            Text(
                text = stringResource(R.string.lbl_gender),
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)

            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
                        .clip(
                            shape = RoundedCornerShape(12.dp),
                        )
                        .background(color = inactiveBackground)
                        .border(
                            BorderStroke(
                                width = 1.dp,
                                color = if (edtGender == 0) lightGreen else inactiveBackground
                            ), shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { edtGender = 0 },


                    ) {
                    Text(

                        text = stringResource(R.string.male),
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 10.dp)


                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                        .clip(
                            shape = RoundedCornerShape(12.dp),
                        )
                        .background(color = inactiveBackground)
                        .border(
                            BorderStroke(
                                width = 1.dp,
                                color = if (edtGender == 1) lightGreen else inactiveBackground
                            ), shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { edtGender = 1 },


                    ) {
                    Text(

                        text = stringResource(R.string.female),
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 10.dp)


                    )
                }
            }


            var edtPassword1 by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.new_password),
                placeHolder = stringResource(R.string.enter_password),
                text = { edtPassword1 },
                onTextChange = { edtPassword1 = it },
                keyboardType = KeyboardType.Password
            )
            var edtPassword2 by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.confirm_password),
                placeHolder = stringResource(R.string.enter_password_again),
                text = { edtPassword2 },
                onTextChange = { edtPassword2 = it },
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            )




            Button(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 24.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightGreen),
                enabled = checkNumber(edtPhoneNumber)
                        && edtFirstName.length > 3
                        && edtLastName.length > 3
                        && edtAge.isNotEmpty()
                        && edtPassword1.length > 3
                        && uiState.value.btnRegisterState
                        && edtPassword2 == edtPassword1 ,
                onClick = {
                    eventDispatcher.invoke(
                        Intent.RegisterUser(
                            phoneNumber = edtPhoneNumber,
                            name = edtFirstName,
                            surname = edtLastName,
                            age = edtAge,
                            gender = "$edtGender" ,
                            password = edtPassword1,
                        )
                    )
                }
            ) {

                Text(text = stringResource(R.string.lbl_continue),color = Color.White)
            }

        }
    }


}