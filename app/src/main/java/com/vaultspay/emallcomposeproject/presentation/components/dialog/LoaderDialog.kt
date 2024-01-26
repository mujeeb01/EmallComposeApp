package com.vaultspay.emallcomposeproject.presentation.components.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vaultspay.emallcomposeproject.R

@Composable
fun LoaderDialog() {
    Dialog(onDismissRequest = {}) {
        Surface(modifier = Modifier.size(128.dp)) {
            val preloaderLottieComposition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(
                    R.raw.emall_loading_m
                )
            )

            val preloaderProgress by animateLottieCompositionAsState(
                preloaderLottieComposition,
                iterations = LottieConstants.IterateForever,
                isPlaying = true,
                restartOnPlay = true,
                speed = 1f,
            )
            LottieAnimation(
                composition = preloaderLottieComposition,
                progress = preloaderProgress,
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}