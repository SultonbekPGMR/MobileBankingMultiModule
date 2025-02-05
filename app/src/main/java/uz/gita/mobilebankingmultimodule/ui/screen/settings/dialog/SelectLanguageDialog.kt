package uz.gita.mobilebankingmultimodule.ui.screen.settings.dialog

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.MobileBankingMultiModuleTheme
import uz.gita.mobilebankingmultimodule.ui.theme.green
import uz.gita.mobilebankingmultimodule.ui.util.LanguageChangeHelper

/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

class SelectLanguageDialog : Screen {

    var onLanguageSelected: (String) -> Unit = {}
    var onDismiss: () -> Unit = {}

    @Composable
    override fun Content() {
        SelectLanguageDialogContent()
    }

    @Preview
    @Composable
    private fun SelectLanguageDialogScreenPreview() {
        MobileBankingMultiModuleTheme {
            SelectLanguageDialogContent()
        }
    }

    private val languages = listOf("English", "Русский", "O'zbek")
    private val flags = listOf(R.drawable.flag_en, R.drawable.flag_ru, R.drawable.flag_uz)

    @Composable
    private fun SelectLanguageDialogContent() {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
                .padding(bottom = 16.dp)
        ) {


            var selectedIndex by remember { mutableIntStateOf(0) }

            LaunchedEffect(Unit) {
                selectedIndex = when (LanguageChangeHelper.getCurrentLanguage()) {
                    "en" -> 0
                    "ru" -> 1
                    else -> 2
                }
            }



            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.lbl_language),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        onDismiss.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }


            languages.forEachIndexed { index, language ->
                HorizontalDivider(
                    color = green,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(2.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (selectedIndex == index),
                            onClick = {
                                selectedIndex = index
                                onLanguageSelected.invoke(
                                    when(index){
                                        0 -> "en"
                                        1 -> "ru"
                                        else -> "uz"
                                    }
                                )
                            },
                            role = RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(flags[index]),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = language,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )
                    RadioButton(
                        selected = (selectedIndex == index),
                        onClick = null
                    )

                }


            }


        }
    }


}
