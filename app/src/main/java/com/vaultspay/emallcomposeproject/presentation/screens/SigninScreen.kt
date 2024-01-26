package com.vaultspay.emallcomposeproject.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vaultspay.emallcomposeproject.presentation.components.CountryPickerBottomSheet
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.data.Resource
import com.vaultspay.emallcomposeproject.domain.models.auth.signin.SigninResponse
import com.vaultspay.emallcomposeproject.presentation.components.dialog.LoaderDialog
import com.vaultspay.emallcomposeproject.ui.theme.colorGreyExLight
import com.vaultspay.emallcomposeproject.ui.theme.colorGreyLight
import com.vaultspay.emallcomposeproject.ui.theme.colorPrimary
import com.vaultspay.emallcomposeproject.ui.theme.colorPrimaryDark
import com.vaultspay.emallcomposeproject.ui.theme.textColorDark
import com.vaultspay.emallcomposeproject.ui.theme.textColorLight
import com.vaultspay.emallcomposeproject.utils.countryList
import com.vaultspay.emallcomposeproject.utils.localeToEmoji

var numberCode : String = ""
var phoneNumber : String = ""

@Composable
fun SigninScreen(navController: NavHostController, viewModel: SigninViewModel = hiltViewModel()) {
    val loginResponseState by viewModel.loginRequest.collectAsState()

    when(loginResponseState) {
        is Resource.Loading -> Column(modifier = Modifier.fillMaxSize()) {
            LoaderDialog()
        }
        is Resource.Success -> {
            Log.d("signin_api", (loginResponseState as Resource.Success<SigninResponse>).data.message)
        }
        is Resource.Error -> {
            Log.d("signin_api", (loginResponseState as Resource.Error<SigninResponse>).error.message.toString())
        }
        else -> null
    }
    Column(modifier = Modifier.fillMaxSize()) {
        BuildHeaderLogo()
        BuildMiddleText(modifier = Modifier)
        Spacer(modifier = Modifier.height(10.dp))
        BuildEmailNumber(modifier = Modifier)
        BuildPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp)
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
            hint = "Password"
        )
        BuildForgetPassField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 20.dp)
        )
        BuildSignInButton(
            modifier = Modifier.fillMaxWidth(),
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(10.dp))
        BuildDividerNText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            title = "Or continue with"
        )
        Spacer(modifier = Modifier.height(20.dp))
        BuildSocialIcons(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        BuildSignUpText(
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(20.dp))
        BuildDividerNText(
            modifier = Modifier
                .fillMaxWidth(),
            title = "Sell with Emall"
        )
        Spacer(modifier = Modifier.height(20.dp))
        BuildBecomeSeller(
            modifier = Modifier
                .fillMaxWidth(),
            "Become a Seller"
        )
    }

}

@Composable
fun BuildHeaderLogo() {
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

@Composable
fun LottieAnimations(modifier: Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.signup_lottie
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = true,
        speed = 1f,
    )

    // Wrap the LottieAnimation with a Box and apply gradient as background
    Box(
        modifier = modifier
            .fillMaxWidth()
        //.background(colorPrimary)
    ) {
        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
            modifier = modifier
                .fillMaxWidth(),
            //.height(82.dp),
            contentScale = ContentScale.Crop
        )

        // Apply gradient to the bottom of the Box
        Box(
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, colorPrimaryDark),
                        startY = 100f,
                        endY = 600f
                    )
                )
        )
    }
}

@Composable
fun BuildMiddleText(modifier: Modifier) {
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
            text = "Sign in to your emall account and enjoy shopping",
            color = textColorLight,
            fontSize = 9.6.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
        )
    }
}

@Composable
fun BuildEmailNumber(modifier: Modifier) {
    var isEmailClicked by remember { mutableStateOf(true) }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .size(80.dp)
            .background(Color.Transparent)
            .padding(start = 20.dp, end = 20.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(1.dp, colorGreyLight, shape = RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable {
                        isEmailClicked = true
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.email_id),
                    contentDescription = "email id",
                    colorFilter = if (isEmailClicked) ColorFilter.tint(colorPrimary)
                    else ColorFilter.tint(textColorDark)
                )
                Text(
                    text = "Use Email ID",
                    color = if (isEmailClicked) colorPrimary else textColorDark,
                    fontSize = 9.6.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                )
            }

            Box(
                modifier
                    .width(1.dp)
                    .background(colorGreyLight)
                    .padding(top = 8.dp, bottom = 8.dp)
            )

            Row(
                modifier = modifier
                    .weight(1f)
                    .clickable {
                        isEmailClicked = false
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.phone_icon),
                    contentDescription = "phone icon",
                    colorFilter = if (!isEmailClicked) ColorFilter.tint(colorPrimary)
                    else ColorFilter.tint(textColorDark)
                )
                Text(
                    text = "Use Phone Number",
                    color = if (!isEmailClicked) colorPrimary else textColorDark,
                    fontSize = 9.6.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                )
            }
        }

        Box() {
            Divider(
                color = colorGreyLight,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = if (isEmailClicked) colorPrimary else colorGreyLight,
                    thickness = if (isEmailClicked) 1.5.dp else 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Divider(
                    color = if (!isEmailClicked) colorPrimary else colorGreyLight,
                    thickness = if (!isEmailClicked) 1.5.dp else 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }

        if (isEmailClicked) BuildEmailField(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            hint = "Email ID",
            inputType = false
        )
        else Row(
            modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var isOpened by rememberSaveable {
                mutableStateOf(false)
            }
            Row(
                //modifier.weight(0.3f),
                modifier = modifier
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
                    modifier = modifier.padding(start = 5.dp, end = 10.dp),
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
                modifier
                    .width(1.dp)
                    .background(colorGreyLight)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Row(
                modifier
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
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildNumberField(
    modifier: Modifier,
    hint: String,
    inputType: Boolean
) {
    val inputValue = remember { mutableStateOf("") }
    val colors = TextFieldDefaults.textFieldColors()
    BasicTextField(
        value = inputValue.value,
        onValueChange = {
            if (it.length <= 10) {
                phoneNumber = it
                inputValue.value = it
            }
        },
        textStyle = TextStyle(
            fontSize = 9.6.sp,
            color = textColorDark,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        ),
        modifier = modifier
            .background(
                color = Color.Transparent,
            )
            .indicatorLine(
                enabled = false,
                isError = false,
                interactionSource = remember { MutableInteractionSource() },
                colors = colors,
                focusedIndicatorLineThickness = 0.dp,  //to hide the indicator line
                unfocusedIndicatorLineThickness = 0.dp //to hide the indicator line
            )
            .fillMaxHeight(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Number
        ),
        cursorBrush = SolidColor(colorPrimary),
        interactionSource = remember { MutableInteractionSource() },
        enabled = true,
        singleLine = true,
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = inputValue.value,
            innerTextField = it,
            singleLine = true,
            enabled = false,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = hint,
                    fontSize = 9.6.sp,
                    color = textColorDark,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            },
            interactionSource = remember { MutableInteractionSource() },
            // keep horizontal paddings but change the vertical
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 0.dp, bottom = 0.dp, start = 0.dp
            ),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = colorPrimary,
                disabledTextColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                disabledLabelColor = Color.Transparent,
                disabledPlaceholderColor = Color.Transparent,
                disabledSupportingTextColor = Color.Transparent,
                disabledPrefixColor = Color.Transparent,
                disabledSuffixColor = Color.Transparent,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildEmailField(
    modifier: Modifier,
    hint: String,
    inputType: Boolean
) {
    val inputValue = remember { mutableStateOf("") }
    val colors = TextFieldDefaults.textFieldColors()
    BasicTextField(
        value = inputValue.value,
        onValueChange = {
            inputValue.value = it
        },
        textStyle = TextStyle(
            fontSize = 9.6.sp,
            color = textColorDark,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        ),
        modifier = modifier
            .background(
                color = Color.Transparent,
            )
            .indicatorLine(
                enabled = false,
                isError = false,
                interactionSource = remember { MutableInteractionSource() },
                colors = colors,
                focusedIndicatorLineThickness = 0.dp,  //to hide the indicator line
                unfocusedIndicatorLineThickness = 0.dp //to hide the indicator line
            )
            .fillMaxHeight(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Email
        ),
        cursorBrush = SolidColor(colorPrimary),
        interactionSource = remember { MutableInteractionSource() },
        enabled = true,
        singleLine = true,
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = inputValue.value,
            innerTextField = it,
            singleLine = true,
            enabled = false,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = hint,
                    fontSize = 9.6.sp,
                    color = textColorDark,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            },
            interactionSource = remember { MutableInteractionSource() },
            // keep horizontal paddings but change the vertical
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 0.dp, bottom = 0.dp,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = colorPrimary,
                disabledTextColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                disabledLabelColor = Color.Transparent,
                disabledPlaceholderColor = Color.Transparent,
                disabledSupportingTextColor = Color.Transparent,
                disabledPrefixColor = Color.Transparent,
                disabledSuffixColor = Color.Transparent,
            ),
        )
    }
}

@Composable
fun BuildPasswordField(
    modifier: Modifier,
    hint: String
) {
    val inputValue = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            //{ Icon(imageVector = Icons.Default.AddCircle, contentDescription = "passwordIcon") },
            Image(
                if (isPasswordVisible) painterResource(id = R.drawable.password_hide) else painterResource(
                    id = R.drawable.password_visible
                ),
                contentDescription = "password icon",
            )
            /*Icon(
                if (isPasswordVisible) Icons.Default.AddCircle else Icons.Default.Email,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )*/
        }
    }

    OutlinedTextField(
        value = inputValue.value,
        onValueChange = {
            inputValue.value = it
        },
        placeholder = {
            Text(
                text = hint,
                fontSize = 9.6.sp,
                color = textColorDark,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        },
        modifier = modifier
            .background(Color.Transparent)
            .border(
                border = BorderStroke(width = 1.dp, color = colorGreyLight),
                shape = RoundedCornerShape(size = 4.1.dp)
            ),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorPrimary,
            focusedBorderColor = colorGreyLight,
            disabledBorderColor = colorGreyLight
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        textStyle = TextStyle(
            color = textColorLight,
            fontSize = 9.6.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        ),
        trailingIcon = trailingIcon,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        maxLines = 1,
        singleLine = true,
    )
}

@Composable
fun BuildForgetPassField(modifier: Modifier) {
    Text(
        text = "Forget Password?",
        modifier = modifier.fillMaxWidth(),
        color = colorPrimary,
        fontSize = 9.6.sp,
        lineHeight = 13.3.sp,
        textAlign = TextAlign.End,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
    )
}

@Composable
fun BuildSignInButton(
    modifier: Modifier,
    viewModel: SigninViewModel
) {
    Button(
        onClick = {
            viewModel.loginRequest(email = "msb.2905@gmail.com", password = "Test@123")
        },
        enabled = true,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorPrimary,
            contentColor = Color.White
        ),
        modifier = modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Sign In",
            modifier = modifier.fillMaxWidth(),
            color = Color.White,
            fontSize = 9.6.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
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
fun BuildSignUpText(modifier: Modifier) {
    Box(
        modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val signUpText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("New to emall? ")
            }
            withStyle(
                style = SpanStyle(
                    color = colorPrimary,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight.Bold
                )

            ) {
                append("Sign Up")
                addClickableStyle(
                    onClick = { offset ->
                        // Handle click on "Sign Up" text
                    }
                )
            }
        }

        ClickableText(
            text = signUpText,
            modifier = Modifier.clickable { /* Handle click on the entire text if needed */ },
            onClick = { offset ->
                // Handle click on the entire text if needed
            }
        )
    }
}

@Composable
private fun AnnotatedString.Builder.addClickableStyle(
    onClick: (offset: Int) -> Unit
) {
    addStyle(
        style = SpanStyle(
            fontSize = 9.6.sp,
            //textDecoration = TextDecoration.Underline,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
        ),
        start = 0,
        end = length
    )
    addStringAnnotation(
        tag = "Clickable",
        annotation = "Sign Up",
        start = 0,
        end = length
    )
}

@Composable
fun BuildBecomeSeller(
    modifier: Modifier,
    title: String
) {
    Text(
        text = title,
        modifier = modifier,
        color = colorPrimary,
        fontSize = 9.6.sp,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
    )
}

