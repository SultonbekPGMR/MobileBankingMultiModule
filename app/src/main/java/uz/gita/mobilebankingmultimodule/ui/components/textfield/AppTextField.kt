package uz.gita.mobilebankingmultimodule.ui.components.textfield

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */


@Composable
fun AppTextField(
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
        color = MaterialTheme.colorScheme.primary,
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
                                tint = MaterialTheme.colorScheme.primary
                            )

                        }

                        else -> {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear text",
                                tint = MaterialTheme.colorScheme.primary
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
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )

}