package uz.gita.mobilebankingmultimodule.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import uz.gita.mobilebankingmultimodule.ui.util.ThemeHelper
import uz.gita.mobilebankingmultimodule.ui.util.ThemeHelper.darkTheme

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    tertiary = Color.White,
    tertiaryContainer = Color.Transparent,

    secondary = Color(0xFF141410),
    secondaryContainer = Color(0xFF1C1C1C),

    surface = Color.Transparent,
    onSurface = Color(0xFF282828),
    surfaceContainer = Color(0xFF282828),
    outlineVariant = green,

    )

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color(0xFFEAF0EB),
    tertiary = gray,
    tertiaryContainer = whiteLighter,
    secondaryContainer = Color.White,
    surface = Color.Transparent,
    onSurface = Color.White,
    surfaceContainer = Color.White,
    outlineVariant = whiteLighter


)


@Composable
fun MobileBankingMultiModuleTheme(
    darkTheme: Boolean = ThemeHelper.darkTheme.value?: isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}