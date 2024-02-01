package com.vaultspay.emallcomposeproject.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.ui.theme.colorGreyLight
import com.vaultspay.emallcomposeproject.ui.theme.colorPrimary
import com.vaultspay.emallcomposeproject.ui.theme.textColorDark
import com.vaultspay.emallcomposeproject.ui.theme.textColorLight

@Composable
fun BuildInputField(
    modifier: Modifier,
    hint: String,
    isTrailingIcon: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    val inputValue = rememberSaveable { mutableStateOf("") }
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
            onValueChange(it)
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
            .clip(shape = RoundedCornerShape(5.dp))
            .border(border = BorderStroke(width = 1.dp, color = colorGreyLight),
            shape = RoundedCornerShape(size = 5.dp)
        ),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorPrimary,
            focusedBorderColor = colorGreyLight,
            disabledBorderColor = colorGreyLight,
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            imeAction = ImeAction.Done,
            keyboardType = keyboardType
        ),
        textStyle = TextStyle(
            color = textColorLight,
            fontSize = 9.6.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        ),
        trailingIcon = if (isTrailingIcon) trailingIcon else null,
        visualTransformation = if (keyboardType == KeyboardType.Password) {
            if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        maxLines = 1,
        singleLine = true,
    )
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun MyTextFieldPreview() {
    Surface {
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
    }
}