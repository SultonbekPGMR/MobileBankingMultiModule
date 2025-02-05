package uz.gita.mobilebankingmultimodule.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.common.navigator.AppNavigatorHandler
import uz.gita.mobilebankingmultimodule.ui.screen.auth.splash.SplashScreen
import uz.gita.mobilebankingmultimodule.ui.theme.MobileBankingMultiModuleTheme
import uz.gita.mobilebankingmultimodule.ui.util.LanguageChangeHelper
import uz.gita.mobilebankingmultimodule.ui.util.ThemeHelper
import uz.gita.mobilebankingmultimodule.ui.util.ThemeHelper.darkTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigatorHandler: AppNavigatorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        navigatorHandler.toastStack.onEach {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

        setContent {

            MobileBankingMultiModuleTheme(
                darkTheme = ThemeHelper.darkTheme.collectAsState().value ?: isSystemInDarkTheme()
            ) {
                Log.d("FNJJFSKNJFNFDKNJFDSKNJFNKDJS", "onCreate: worked -> ${darkTheme.collectAsState().value ?: isSystemInDarkTheme()}")

                BottomSheetNavigator(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.outlineVariant
                    ),
                    sheetShape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                ) {
                    Navigator(SplashScreen()) { navigator: Navigator ->
                        LaunchedEffect(key1 = navigator) {
                            navigatorHandler.navigation
                                .collect {
                                    it(navigator)
                                }
                        }
                        SlideTransition(navigator)
                    }
                }

            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageChangeHelper.attach(newBase))
    }


}


