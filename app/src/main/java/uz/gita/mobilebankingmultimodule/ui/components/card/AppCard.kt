package uz.gita.mobilebankingmultimodule.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.common.model.CardData
import uz.gita.common.util.formatBalance
import uz.gita.common.util.formatCardPan
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen


/**
 * Created by Sultonbek Tulanov on 03/01/2025.
 */



@Composable
fun AppCard(
    modifier: Modifier = Modifier
        .clip(shape = RoundedCornerShape(12.dp))
        .background(color = MaterialTheme.colorScheme.onSurface),
    isSelected: Boolean = false,
    cardData: CardData,
    ownerNameVisibility: Boolean = true,
    onClick: () -> Unit,
) {

    Card(
        border = BorderStroke(width = if (isSelected) 2.dp else (-1).dp, color = lightGreen),

        modifier = modifier
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onSurface)
                .padding(12.dp)
                .padding(end = 25.dp)
        ) {
            Text(
                text = cardData.name,
                fontSize = 14.sp,
                color = lightGreen
            )

//            if (ownerNameVisibility) {
//                Text(
//                    modifier = Modifier
//                        .padding( bottom = 1.dp),
//                    text = cardData.name,
//                    fontSize = 12.sp,
//                    color = gray
//                )
//            }
//            else Spacer(modifier = Modifier.padding(2.dp))

            Text(
                modifier = Modifier.padding(top = 1.dp, bottom = 5.dp),
                text = formatBalance(cardData.amount.toDouble()),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = formatCardPan(cardData.pan),
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = gray
            )


        }

    }

}
