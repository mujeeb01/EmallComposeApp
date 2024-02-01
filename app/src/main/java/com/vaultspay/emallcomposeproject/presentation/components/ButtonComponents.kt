package com.vaultspay.emallcomposeproject.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.ui.theme.colorPrimary

@Composable
fun BuildButton(
    modifier: Modifier,
    buttonText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = true,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorPrimary,
            contentColor = Color.White
        ),
        modifier = modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)
    ) {
        Text(
            text = buttonText,
            modifier = modifier.fillMaxWidth(),
            color = Color.White,
            fontSize = 9.6.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
        )
    }
}