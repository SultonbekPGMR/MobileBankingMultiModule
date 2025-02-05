package uz.gita.mobilebankingmultimodule.ui.screen.auth.selectlanguage

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.mobilebankingmultimodule.ui.theme.backgroundGradient
import uz.gita.mobilebankingmultimodule.ui.theme.green
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.util.LanguageChangeHelper
import uz.gita.presenter.contract.SelectLanguageContract
import uz.gita.presenter.contract.SelectLanguageContract.*
import uz.gita.presenter.viewmodel.SelectLanguageViewModel


/**
 * Created by Sultonbek Tulanov on 25/12/2024.
 */

class SelectLanguageScreen : Screen {
    private val languages = listOf("English", "Русский", "O'zbek")
    private val flags = listOf(R.drawable.flag_en, R.drawable.flag_ru, R.drawable.flag_uz)

    @Composable
    override fun Content() {
        val viewModel: SelectLanguageContract.ViewModel = getScreenModel<SelectLanguageViewModel>()
        SelectLanguageScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }

    @Preview
    @Composable
    private fun SelectLanguageScreenPreview() {
        SelectLanguageScreenContent()
    }


    @Composable
    private fun SelectLanguageScreenContent(
        uiState: State<SelectLanguageContract.UiState> = remember {
            mutableStateOf(
                SelectLanguageContract.UiState()
            )
        },
        eventDispatcher: (Intent) -> Unit = {},
    ) {
        var selectedIndex by remember { mutableIntStateOf(0) }
        val context = LocalContext.current



        LaunchedEffect(Unit) {
            selectedIndex = when (LanguageChangeHelper.getCurrentLanguage()) {
                "en" -> 0
                "ru" -> 1
                else -> 2
            }
        }


        key(selectedIndex){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundGradient)
            ) {
                SideEffect {

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(410.dp)
                        .alpha(0.1f)

                        .paint(
                            painterResource(id = R.drawable.ic_vector),
                            contentScale = ContentScale.FillBounds
                        )
                        .align(Alignment.Center)

                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.welcome_to_xazna),
                        color = Color.White,
                        fontSize = 21.sp,
                    )

                    Text(
                        text = stringResource(R.string.choose_lang),
                        color = Color.White,
                        fontSize = 13.sp
                    )




                    LazyRow {
                        items(3) {
                            Log.d("TTTT", "SelectLanguageScreenContent: ")

                            RvLanguageItem(languages[it], flags[it], selectedIndex == it) {
                                selectedIndex = it

                                LanguageChangeHelper.setLanguage(
                                    context,
                                    getLangCode(it)
                                )


                            }
                        }

                    }


                    Spacer(modifier = Modifier.weight(1f))


                    OutlinedButton (
                        shape = RoundedCornerShape(12.dp),

                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 48.dp)
                            .fillMaxWidth()

                            .height(50.dp),
                        border = BorderStroke(2.dp, lightGreen),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = {
                            eventDispatcher.invoke(Intent.OpenNextScreen)
                        },
                        enabled = uiState.value.btnNextState
                    ) {

                        Text(text = stringResource(R.string.lbl_continue),color = Color.White)
                    }

                }


            }
        }


    }

    private fun getLangCode(index: Int): String {
        return when (index) {
            0 -> "en"
            1 -> "ru"
            else -> "uz"
        }
    }


    @Composable
    private fun RvLanguageItem(name: String, resId: Int, isSelected: Boolean, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .height(50.dp)
                .padding(5.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(green)
                .clickable { onClick() }
        ) {
            Image(
                painter = painterResource(resId),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )

            if (!isSelected) return

            Text(
                text = name,
                color = Color.White,

                modifier = Modifier.padding(8.dp)
            )

            Image(
                painter = painterResource(R.drawable.ic_done),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}

