package uz.gita.mobilebankingmultimodule.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.common.model.CardData
import uz.gita.common.util.formatBalance
import uz.gita.common.util.formatCardPan
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.util.background


/**
 * Created by Sultonbek Tulanov on 03/01/2025.
 */



@Composable
fun AppCardBig(
    modifier: Modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .aspectRatio(2f)
        .clip(shape = RoundedCornerShape(12.dp))
        .background(color = MaterialTheme.colorScheme.onSurface),
    isSelected: Boolean = false,
    cardData: CardData,
    ownerNameVisibility: Boolean = true,
) {

    Card(
        border = BorderStroke(width = if (isSelected) 2.dp else (-1).dp, color = lightGreen),

        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    resId = R.drawable.bg_card_1,
                )
                .padding(12.dp)
                .padding(end = 25.dp)
        ) {
            Text(
                text = cardData.name,
                fontSize = 14.sp,
                color = Color.White

            )
            Spacer(modifier = Modifier.weight(1f))


            Text(
                modifier = Modifier.padding(top = 1.dp, bottom = 5.dp),
                text = formatBalance(cardData.amount.toDouble()),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(2f))

            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = formatCardPan(cardData.pan),
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                color = Color.White
            )


        }

    }

}
