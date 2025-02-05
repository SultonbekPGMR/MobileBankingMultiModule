package uz.gita.mobilebankingmultimodule.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.inactiveBackground


/**
 * Created by Sultonbek Tulanov on 25/12/2024.
 */


@Composable
fun BtnBack(onclick: () -> Unit) {
    Image(
        painter = painterResource(R.drawable.ic_action_back_new),
        contentDescription = "Back",
        modifier = Modifier
            .padding(24.dp)
            .clickable(true) {
                onclick.invoke()
            }
    )
}


@Composable
fun CustomTextField(
    label: String,
    placeHolder: String,
    text: () -> String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Next,
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Text(
        text = label,
        fontSize = 14.sp,
        color = Color.White,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

    )

    TextField(
        value = text(),
        onValueChange = { onTextChange(it) },
        label = null,
        placeholder = { Text(text = placeHolder) },
        visualTransformation = if (keyboardType == KeyboardType.Password && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        trailingIcon = {

            if (text().isNotEmpty()) {

                IconButton(
                    onClick = {
                        if (keyboardType == KeyboardType.Password) isPasswordVisible =
                            !isPasswordVisible
                        else onTextChange("")
                    }) {
                    when (keyboardType) {
                        KeyboardType.Password -> {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = "Clear text",
                                tint = Color.White
                            )

                        }

                        else -> {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear text",
                                tint = Color.White
                            )


                        }
                    }
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedContainerColor = inactiveBackground,
            focusedContainerColor = inactiveBackground,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )

}