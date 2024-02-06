package com.vaultspay.emallcomposeproject.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.ui.theme.textColorDark
import com.vaultspay.emallcomposeproject.ui.theme.textColorExtraLight
import com.vaultspay.emallcomposeproject.ui.theme.textColorLight

@Composable
fun AuthMiddleText(modifier: Modifier, textBottom: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Hello, welcome to ",
                color = textColorDark,
                fontSize = 13.56.sp,
                lineHeight = 13.3.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
            )
            Image(
                painter = painterResource(id = R.drawable.emall_logo),
                contentDescription = "emall logo",
                modifier.height(14.dp),
                alignment = Alignment.Center
            )
            Text(
                text = "!",
                color = textColorDark,
                fontSize = 13.56.sp,
                lineHeight = 13.3.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
            )
        }
        Text(
            text = textBottom,
            color = textColorLight,
            fontSize = 9.6.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
        )
    }
}

@Composable
fun BuildNormalText(modifier: Modifier, value: String) {
    Text(
        text = value,
        modifier = modifier,
        color = textColorExtraLight,
        fontSize = 7.2.sp,
        lineHeight = 9.5.sp,
        //textAlign = TextAlign.Center,
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
    )
}