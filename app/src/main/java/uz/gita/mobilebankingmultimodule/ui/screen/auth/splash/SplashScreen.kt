package uz.gita.mobilebankingmultimodule.ui.screen.auth.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import uz.gita.mobilebankingmultimodule.ui.theme.backgroundGradient
import uz.gita.mobilebankingmultimodule.util.paddingNegative
import uz.gita.presenter.contract.SplashContract.Intent.Navigate
import uz.gita.presenter.viewmodel.SplashVIewModel


/**
 * Created by Sultonbek Tulanov on 25/12/2024.
 */


class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<SplashVIewModel>()

        SplashScreenContent() {
            viewModel.onEventDispatcher(Navigate)
        }

    }

    @Preview
    @Composable
    fun SplashScreenPreview() {
        SplashScreenContent()
    }


    @Composable
    fun SplashScreenContent(navigate: () -> Unit = {}) {
        LaunchedEffect(Unit) {
            delay(1)
            Log.d("Splash", "SplashScreenContent: ")
            navigate()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient)
        ) {
            val composition1 by rememberLottieComposition(LottieCompositionSpec.Asset("xazna_logo.json"))
            val progress1 =
                animateLottieCompositionAsState(composition1, iterations = 1, speed = 2.5f)

            LottieAnimation(
                composition = composition1,
                progress = { progress1.progress },
                modifier = Modifier
                    .wrapContentSize()
                    .aspectRatio(0.5f)
            )

            val composition by rememberLottieComposition(LottieCompositionSpec.Asset("splash_lines.json"))
            val progress =
                animateLottieCompositionAsState(composition, iterations = 1, speed = 2.5f)

            LottieAnimation(
                composition = composition,
                progress = { progress.progress },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .aspectRatio(0.5f)
                    .paddingNegative(horizontal = 16.dp),
                alignment = Alignment.BottomCenter
            )
        }
    }


}