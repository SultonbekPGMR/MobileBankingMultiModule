package uz.gita.mobilebankingmultimodule.ui.components.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebankingmultimodule.ui.util.CardNumberMask
import uz.gita.mobilebankingmultimodule.ui.theme.gray
import uz.gita.mobilebankingmultimodule.ui.theme.lightGreen
import uz.gita.mobilebankingmultimodule.ui.theme.whiteVeryLight


/**
 * Created by Sultonbek Tulanov on 04/01/2025.
 */


@Composable
fun TextFieldCardNumber(
    modifier: Modifier = Modifier,
    text: State<String>,
    containerColor: Color = MaterialTheme.colorScheme.outlineVariant,
    leadingIconVisibility: Boolean = true,
    onTextChange: (String) -> Unit,
) {


    OutlinedTextField(
        value = text.value,
        onValueChange = { onTextChange(it.filter { char -> char.isDigit() }) },
        label = null,
        visualTransformation = CardNumberMask(separator = " ", digitMask = "0"),
        textStyle = TextStyle.Default.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            letterSpacing = TextUnit(0.5f, TextUnitType.Sp)
        ),
        modifier = modifier
            .border(border = BorderStroke(1.dp, lightGreen), shape = RoundedCornerShape(12.dp)),
        leadingIcon = {
            if (leadingIconVisibility) IconButton(
                onClick = {
                    onTextChange("")
                }) {
                Icon(
                    imageVector = Icons.Default.ContactPage,
                    contentDescription = "",
                    tint = lightGreen
                )

            }
        },
        shape = RoundedCornerShape(12.dp),

        trailingIcon = {

            if (text.value.isNotEmpty()) {

                IconButton(
                    onClick = {
                        onTextChange("")
                    }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear text",
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            }
        },
        placeholder = {
            Text(
                text = "0000 0000 0000 0000",
                color = gray
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        singleLine = true,

        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = containerColor,
            focusedContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )

}


@Composable
fun TextFieldCardNumberWithoutLeadingIcon(
    modifier: Modifier = Modifier,
    text: State<String>,
    containerColor: Color = MaterialTheme.colorScheme.outlineVariant,
    onTextChange: (String) -> Unit,
) {


    OutlinedTextField(
        value = text.value,
        onValueChange = { onTextChange(it.filter { char -> char.isDigit() }) },
        label = null,

        placeholder = {
            Text(
                text = "0000 0000 0000 0000",
                color = gray
            )
        },
        visualTransformation = CardNumberMask(separator = " ", digitMask = "0"),
        textStyle = TextStyle.Default.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            letterSpacing = TextUnit(0.5f, TextUnitType.Sp)
        ),
        modifier = modifier
            .border(border = BorderStroke(1.dp, whiteVeryLight), shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),

        trailingIcon = {

            if (text.value.isNotEmpty()) {

                IconButton(
                    onClick = {
                        onTextChange("")
                    }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear text",
                        tint = Color.White
                    )

                }
            }
        },
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
            unfocusedIndicatorColor = Color.Transparent,

            )
    )

}
