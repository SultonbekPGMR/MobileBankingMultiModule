package uz.gita.mobilebankingmultimodule.ui.components.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebankingmultimodule.ui.util.ExpirationDateMask
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.whiteVeryLight

/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

@Composable
fun TextFieldCardExpireDate(
    modifier: Modifier = Modifier,
    text: State<String>,
    containerColor: Color = MaterialTheme.colorScheme.outlineVariant,
    leadingIconVisibility: Boolean = true,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(

        placeholder = {
            Text(
                text = "MM/YY",
                color = gray
            )
        },
        value = text.value,
        onValueChange = { onTextChange(it.filter { char -> char.isDigit() }) },
        label = null,
        visualTransformation = ExpirationDateMask(),
        textStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            textAlign = TextAlign.Start
        ),
        modifier = modifier
            .border(
                border = BorderStroke(1.dp, whiteVeryLight),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),


        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedContainerColor = containerColor,
            focusedContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
