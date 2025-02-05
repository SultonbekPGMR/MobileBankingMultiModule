package uz.gita.mobilebankingauthcompose.ui.screen.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen


/**
 * Created by Sultonbek Tulanov on 02/01/2025.
 */

class MenuScreen : Screen {
    @Composable
    override fun Content() {
        MenuScreenContent()
    }

    @Composable
    private fun MenuScreenContent() {
        Column {
            Text(
                text = "MenuScreen",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}