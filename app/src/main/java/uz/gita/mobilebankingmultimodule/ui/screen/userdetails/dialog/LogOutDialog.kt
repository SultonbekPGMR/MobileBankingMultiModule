package uz.gita.mobilebankingmultimodule.ui.screen.userdetails.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.green


/**
 * Created by Sultonbek Tulanov on 06/01/2025.
 */

class LogOutDialog : Screen {

    var onConfirm: () -> Unit = {}
    var onDismiss: () -> Unit = {}


    @Composable
    override fun Content() {
        LogOutDialogContent()
    }


    @Preview
    @Composable
    private fun LogOutDialogPreview() {
        LogOutDialogContent()
    }


    @Composable
    private fun LogOutDialogContent() {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(16.dp).padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.lbl_do_you_really_want_it),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.lbl_exit),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.fillMaxWidth(),

                ) {

                OutlinedButton(
                    modifier = Modifier.weight(1f).padding(16.dp),
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.lbl_no),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,

                    )

                }

                Button(
                    modifier = Modifier.weight(1f).padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = green),
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.lbl_yes),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,

                    )
                }

            }

        }

    }


}