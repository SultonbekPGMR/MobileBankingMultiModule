package uz.gita.mobilebankingmultimodule.ui.screen.auth.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.components.BtnBack
import uz.gita.mobilebankingmultimodule.ui.components.CustomTextField
import uz.gita.mobilebankingmultimodule.ui.theme.backgroundGradient
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.presenter.contract.SignInContract.*
import uz.gita.presenter.contract.SignInContract.Intent.PopBack
import uz.gita.presenter.viewmodel.SignInViewModel


/**
 * Created by Sultonbek Tulanov on 25/12/2024.
 */

class SignInScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getScreenModel<SignInViewModel>()
        SignInScreenContent(viewModel.uiState.collectAsState(), viewModel::onEventDispatcher)
    }


    @Composable
    private fun SignInScreenContent(
        uiState: State<UiState>,
        eventDispatcher: (Intent) -> Unit,
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
                text = stringResource(R.string.enter_app),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),

                )
            Text(
                text = stringResource(R.string.enter_app_to_enjoy),
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp, top = 8.dp, start = 16.dp, end = 16.dp)
                    .alpha(0.6f),

                )


            var edtPhoneNumber by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.phone_number),
                placeHolder = stringResource(R.string.phone_number),
                text = { edtPhoneNumber },
                onTextChange = { if (it.length <= 13) edtPhoneNumber = it },
                keyboardType = KeyboardType.Phone
            )

            var edtPassword by rememberSaveable { mutableStateOf("") }
            CustomTextField(
                label = stringResource(R.string.password),
                placeHolder = stringResource(R.string.enter_password),
                text = { edtPassword },
                onTextChange = { edtPassword = it },
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,

            )
            Text(
                modifier = Modifier
                    .padding(bottom = 80.dp, top = 8.dp, start = 16.dp, end = 16.dp)
                    .clip(shape = RoundedCornerShape(12.dp))

                    .clickable(true) {
                        eventDispatcher(Intent.OpenRegisterScreen)
                    }
                    .align(Alignment.End)
                    .padding(horizontal = 2.dp)
                    .alpha(0.9f),
                color = Color.White,

                text = stringResource(R.string.lbl_register),
                fontSize = 13.sp,

                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,

                )


            Spacer(modifier = Modifier.weight(1f))

            Button(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 24.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightGreen),
                enabled = uiState.value.btnEnterState,
                onClick = {
                    eventDispatcher(
                        Intent.ValidateUser(
                            phoneNumber = edtPhoneNumber,
                            password = edtPassword
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.enter_app),
                    color = Color.White
                )
            }
        }


    }
}