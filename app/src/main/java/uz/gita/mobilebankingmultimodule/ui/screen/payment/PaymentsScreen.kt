package uz.gita.mobilebankingmultimodule.ui.screen.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen


/**
 * Created by Sultonbek Tulanov on 02/01/2025.
 */

class PaymentsScreen : Screen {
    @Composable
    override fun Content() {
        PaymentsScreenContent()
    }

    @Composable
    private fun PaymentsScreenContent() {
        Column {
            Text(
                text = "PaymentsScreen",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}