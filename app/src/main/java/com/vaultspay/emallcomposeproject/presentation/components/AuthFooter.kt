package com.vaultspay.emallcomposeproject.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.presentation.navigation.Screens
import com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signin.addClickableStyle
import com.vaultspay.emallcomposeproject.ui.theme.colorGreyExLight
import com.vaultspay.emallcomposeproject.ui.theme.colorPrimary

@Composable
fun BuildAuthFooter(
    modifier: Modifier,
    separatorTitle1: String,
    separatorTitle2: String,
    title: String,
    clickableTitle: String,
    onClick: (Int) -> Unit
) {
    Column {
        BuildDividerNText(
            modifier = modifier, title = separatorTitle1
        )
        Spacer(modifier = Modifier.height(20.dp))
        BuildSocialIcons(modifier = modifier)
        Spacer(modifier = Modifier.height(20.dp))
        BuildClickableText(
            modifier = modifier,
            title = title,
            clickableTitle = clickableTitle,
            onClick = onClick
        )
        Spacer(modifier = Modifier.height(20.dp))
        BuildDividerNText(
            modifier = Modifier
                .fillMaxWidth(),
            title = separatorTitle2
        )
    }
}

@Composable
fun BuildDividerNText(
    modifier: Modifier,
    title: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Divider(
            color = colorGreyExLight,
            thickness = 1.dp,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 15.dp)
        )
        Text(
            text = title,
            modifier = modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            color = colorGreyExLight,
            fontSize = 8.04.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
        )

        Divider(
            color = colorGreyExLight,
            thickness = 1.dp,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 15.dp)
        )
    }
}

@Composable
fun BuildSocialIcons(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb_icon),
            contentDescription = "fb icon",
        )
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "google icon",
        )
        Image(
            painter = painterResource(id = R.drawable.apple),
            contentDescription = "apple icon",
        )
    }
}

@Composable
fun BuildClickableText(
    modifier: Modifier,
    title: String,
    clickableTitle: String,
    onClick: (Int) -> Unit
) {
    Box(
        modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val signUpText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append(title)
            }
            withStyle(
                style = SpanStyle(
                    color = colorPrimary,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight.Bold
                )

            ) {
                append(clickableTitle)
                addClickableStyle(
                    onClick = onClick
                )
            }
        }

        ClickableText(
            text = signUpText,
            modifier = Modifier.clickable { /* Handle click on the entire text if needed */ },
            onClick = onClick
        )
    }
}


@Composable
@Preview
fun BuildAuthFooterPreview() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        BuildAuthFooter(
            modifier = Modifier.fillMaxWidth(),
            separatorTitle1 = "Or continue with",
            separatorTitle2 = "Sell with Emall",
            title = "Already have an account? ",
            clickableTitle = "Sign in",
            onClick = { })
    }
}
