package uz.gita.mobilebankingmultimodule.ui.components.card

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.common.model.SuggestedActionBanner
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen


/**
 * Created by Sultonbek Tulanov on 03/01/2025.
 */


@Composable
fun BannerCard(
    modifier: Modifier = Modifier
        .clip(shape = RoundedCornerShape(17.8.dp))
        .background(color = MaterialTheme.colorScheme.onSurface)
        .fillMaxWidth()
        .height(100.dp)
        .border(border = BorderStroke(1.dp, lightGreen)),
    bannerData: SuggestedActionBanner,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg_banner),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = stringResource(bannerData.titleId),
                    fontWeight = FontWeight.Bold,

                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = stringResource(bannerData.contentId),
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    color = Color.White
                )

            }
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(bannerData.iconId),
                contentDescription = null
            )

        }
    }


}