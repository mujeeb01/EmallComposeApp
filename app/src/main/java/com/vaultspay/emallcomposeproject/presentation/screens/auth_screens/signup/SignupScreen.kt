package com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signup

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.presentation.components.AuthHeaderLogoAnim
import com.vaultspay.emallcomposeproject.presentation.components.AuthMiddleText
import com.vaultspay.emallcomposeproject.presentation.components.BuildButton
import com.vaultspay.emallcomposeproject.presentation.components.BuildInputField
import com.vaultspay.emallcomposeproject.presentation.components.BuildNormalText
import com.vaultspay.emallcomposeproject.presentation.components.CountryPickerBottomSheet
import com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signin.BuildNumberField
import com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signin.numberCode
import com.vaultspay.emallcomposeproject.ui.theme.colorGreyLight
import com.vaultspay.emallcomposeproject.ui.theme.textColorDark
import com.vaultspay.emallcomposeproject.utils.countryList
import com.vaultspay.emallcomposeproject.utils.localeToEmoji

@Composable
fun SignupScreen(
    backPressed: () -> Unit
) {

    BuildSignupScreen()
    BackHandler {
        backPressed()
    }
}

@Composable
@Preview
fun BuildSignupScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(

        ) {
            AuthHeaderLogoAnim()
            AuthMiddleText(
                modifier = Modifier,
                textBottom = "Create your emall account and enjoy shopping"
            )
            Spacer(modifier = Modifier.height(10.dp))
            BuildInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp)
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                hint = "Full Name",
                onValueChange = {
                    Log.d("valueEnterd", "BuildSignupScreen: $it")
                }
            )
            BuildInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp)
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                hint = "Email",
                keyboardType = KeyboardType.Email,
                onValueChange = {

                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(47.dp)
                    .background(Color.Transparent)
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .border(1.dp, colorGreyLight, shape = RoundedCornerShape(8.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Row(
                    Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var isOpened by rememberSaveable {
                        mutableStateOf(false)
                    }
                    Row(
                        //modifier.weight(0.3f),
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(start = 15.dp)
                            .clickable { isOpened = true },
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val context = LocalContext.current
                        val countries = remember { countryList(context) }
                        var selectedCountry by remember { mutableStateOf(countries[0]) }
                        selectedCountry?.let {
                            Text(
                                text = localeToEmoji(countryCode = it.code ?: "")
                            )
                        }
                        Text(
                            text = "${selectedCountry?.dialCode}",
                            modifier = Modifier.padding(start = 5.dp, end = 10.dp),
                            color = textColorDark,
                            fontSize = 9.6.sp,
                            lineHeight = 13.3.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        )

                        Image(
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = "arrow down icon",
                        )

                        if (isOpened) {
                            CountryPickerBottomSheet(
                                show = isOpened,
                                countries = countries,
                                onItemSelected = {
                                    selectedCountry = it
                                    numberCode = it?.dialCode.toString()
                                    isOpened = false
                                },
                                onDismissRequest = {
                                    isOpened = false
                                }
                            )
                        }

                    }
                    Box(
                        Modifier
                            .width(1.dp)
                            .background(colorGreyLight)
                            .padding(top = 8.dp, bottom = 8.dp)
                    )
                    Row(
                        Modifier
                            .weight(0.8f)
                            .fillMaxSize(),
                    ) {
                        BuildNumberField(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp),
                            hint = "Phone Number",
                            inputType = false
                        )
                    }
                }
            }

            BuildInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp)
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                hint = "Password",
                isTrailingIcon = true,
                keyboardType = KeyboardType.Password,
                onValueChange = {

                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            BuildNormalText(
                modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                value = "Password must be eight characters long including one uppercase letter, one special character and alphanumeric characters."
            )
            Spacer(modifier = Modifier.height(10.dp))
            BuildButton(
                modifier = Modifier.fillMaxWidth(),
                buttonText = "Sign up",
                onClick = {

                }
            )
        }
    }
}
