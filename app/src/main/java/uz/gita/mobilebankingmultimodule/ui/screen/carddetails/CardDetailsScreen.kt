package uz.gita.mobilebankingmultimodule.ui.screen.carddetails

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.common.model.CardData
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.components.card.AppCardBig
import uz.gita.mobilebankingmultimodule.ui.theme.green
import uz.gita.presenter.contract.CardDetailsContract
import uz.gita.presenter.contract.CardDetailsContract.Intent.OnBackClick
import uz.gita.presenter.viewmodel.CardDetailsViewModel

/**
 * Created by Sultonbek Tulanov on 3/1/2025.
 */

@Parcelize
class CardDetailsScreen(private val cardData: CardData) : Screen, Parcelable {
    @Composable
    override fun Content() {
        val viewModel: CardDetailsContract.ViewModel =
            getScreenModel<CardDetailsViewModel, CardDetailsViewModel.Factory> {
                it.create(cardData)
            }
        CardDetailsScreenContent(
            uiState = viewModel.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher
        )
    }


    @Composable
    private fun CardDetailsScreenContent(
        uiState: State<CardDetailsContract.UiState> = mutableStateOf(CardDetailsContract.UiState()),
        eventDispatcher: (CardDetailsContract.Intent) -> Unit = {},
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = green)
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
            }

            AppCardBig(cardData = uiState.value.cardData)

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Download, contentDescription = null,
                        tint = Color.White,
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = stringResource(R.string.lbl_top_up),
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FileUpload, contentDescription = null,
                        tint = Color.White,
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = stringResource(R.string.send),
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings, contentDescription = null,
                        tint = Color.White,
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = stringResource(R.string.lbl_actions),
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {

            }
        }
    }
}
