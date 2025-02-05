package uz.gita.mobilebankingmultimodule.ui.components.card

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.common.model.CardData
import uz.gita.common.model.LastRecipient
import uz.gita.common.util.formatCardPan
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.util.formatMillisToFullDate


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */


@Composable
fun ReceiverCard(
    modifier: Modifier = Modifier
        .clip(shape = RoundedCornerShape(12.dp))
        .background(color = MaterialTheme.colorScheme.onSurface),
    receiverData: LastRecipient,
    onClick: () -> Unit,
) {

    Card(
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
                modifier = Modifier.padding(vertical = 4.dp),
                text = receiverData.ownerName,
                fontSize = 14.sp,
                color = lightGreen
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = formatCardPan(receiverData.pan),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                maxLines = 1,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 2.dp, bottom = 1.dp),
                text = formatMillisToFullDate(receiverData.time),
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                maxLines = 1,
                color = gray
            )





        }

    }

}
