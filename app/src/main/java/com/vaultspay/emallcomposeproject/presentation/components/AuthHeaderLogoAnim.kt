package com.vaultspay.emallcomposeproject.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signin.LottieAnimations

@Composable
fun AuthHeaderLogoAnim() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .height(265.dp)
            .fillMaxWidth()
    ) {
        LottieAnimations(
            modifier = Modifier
        )
        Image(
            painter = painterResource(id = R.drawable.emall_logo_bg),
            contentDescription = "header bg",
            modifier = Modifier
                .width(73.dp)
                .height(73.dp)
                .align(alignment = Alignment.Center)
        )
        IconButton(onClick = {
            /* Handle navigation icon click */
        }) {
            Image(
                painter = painterResource(id = R.drawable.close),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
            )
        }
    }
}