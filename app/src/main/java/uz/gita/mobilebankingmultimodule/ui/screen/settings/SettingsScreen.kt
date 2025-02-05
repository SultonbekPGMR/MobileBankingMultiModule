package uz.gita.mobilebankingmultimodule.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.screen.settings.dialog.SelectLanguageDialog
import uz.gita.mobilebankingmultimodule.ui.screen.settings.dialog.SelectThemeDialog
import uz.gita.mobilebankingmultimodule.ui.theme.MobileBankingMultiModuleTheme
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.util.LanguageChangeHelper
import uz.gita.mobilebankingmultimodule.ui.util.ThemeHelper
import uz.gita.presenter.contract.SettingsContract
import uz.gita.presenter.contract.SettingsContract.Intent
import uz.gita.presenter.contract.SettingsContract.Intent.*
import uz.gita.presenter.contract.SettingsContract.SideEffect.OpenLanguageSelectionDialog
import uz.gita.presenter.contract.SettingsContract.SideEffect.OpenThemeSelectionDialog
import uz.gita.presenter.contract.SettingsContract.UiState
import uz.gita.presenter.viewmodel.SettingsViewModel

/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: SettingsContract.ViewModel = getScreenModel<SettingsViewModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val context = LocalContext.current
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                OpenLanguageSelectionDialog -> {
                    val dialog = SelectLanguageDialog()
                    dialog.onDismiss = {
                        bottomSheetNavigator.hide()
                    }
                    dialog.onLanguageSelected = {
                        LanguageChangeHelper.setLanguage(
                            context = context, lang = it
                        )
                        viewModel.onEventDispatcher(OnLanguageChanged)
                        bottomSheetNavigator.hide()
                    }
                    bottomSheetNavigator.show(dialog)
                }

                OpenThemeSelectionDialog -> {

                    val dialog = SelectThemeDialog()
                    dialog.onDismiss = {
                        bottomSheetNavigator.hide()
                    }
                    dialog.onThemeSelected = {
                        ThemeHelper.darkTheme.value = when (it) {
                            0 -> false
                            1 -> true
                            else -> null
                        }

                        viewModel.onEventDispatcher(OnThemeChanged)
                        bottomSheetNavigator.hide()
                    }
                    bottomSheetNavigator.show(dialog)
                }
            }
        }

            SettingsScreenContent(
                uiState = viewModel.collectAsState(), eventDispatcher = viewModel::onEventDispatcher
            )


    }

    @Preview
    @Composable
    private fun SettingsScreenPreview() {
        SettingsScreenContent()
    }

    @Composable
    private fun SettingsScreenContent(
        uiState: State<UiState> = mutableStateOf(UiState()),
        eventDispatcher: (Intent) -> Unit = {},
    ) {
        key(uiState.value.languageChanged) {
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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.lbl_settings),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )

                }




                repeat(uiState.value.options.size) {
                    Row(modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(color = MaterialTheme.colorScheme.outlineVariant)
                        .clickable {
                            eventDispatcher(OnOptionSelected(uiState.value.options[it]))
                        }
                        .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Icon(
                            painter = painterResource(uiState.value.options[it].iconId),
                            contentDescription = null,
                            tint = gray
                        )
                        Text(
                            text = stringResource(uiState.value.options[it].titleId),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = TextUnit(0.2f, TextUnitType.Sp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

    }
}
