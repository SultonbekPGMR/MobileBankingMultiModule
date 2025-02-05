package uz.gita.mobilebankingmultimodule.ui.screen.settings.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.green
import uz.gita.mobilebankingmultimodule.ui.util.ThemeHelper

/**
 * Created by Sultonbek Tulanov on 1/31/2025.
 */

class SelectThemeDialog : Screen {
    var onDismiss: () -> Unit = {}
    var onThemeSelected: (index: Int) -> Unit = {}

    @Composable
    override fun Content() {
        SelectThemeDialogContent()
    }

    private val themeNameIds = listOf(R.string.light, R.string.night, R.string.systemic)
    private val icons =
        listOf(Icons.Outlined.LightMode, Icons.Outlined.DarkMode, Icons.Outlined.Settings)

    @Composable
    private fun SelectThemeDialogContent() {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
                .padding(bottom = 16.dp)
        ) {


            var selectedIndex by remember { mutableIntStateOf(0) }

            LaunchedEffect(Unit) {
                selectedIndex = when (ThemeHelper.darkTheme.value) {
                    false -> 0
                    true -> 1
                    else -> 2
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.theme),
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


            themeNameIds.forEachIndexed { index, themeNameId ->
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
                                onThemeSelected.invoke(
                                    index
                                )
                            },
                            role = RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = icons[index],
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = stringResource(themeNameId),
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

    @Preview
    @Composable
    private fun SelectThemeDialogPreview() {
        SelectThemeDialogContent()
    }

}
