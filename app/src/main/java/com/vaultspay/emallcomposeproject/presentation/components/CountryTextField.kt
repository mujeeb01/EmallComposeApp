package com.vaultspay.emallcomposeproject.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.vaultspay.emallcomposeproject.R
import com.vaultspay.emallcomposeproject.domain.models.Country
import com.vaultspay.emallcomposeproject.ui.theme.colorPrimary
import com.vaultspay.emallcomposeproject.ui.theme.textColorDark
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryTextField(
    label: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    expanded: Boolean = false,
    selectedCountry: Country? = null,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    onExpandedChange: () -> Unit
) {
    Row(
        modifier.background(colorPrimary),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (selectedCountry == null) "" else "${selectedCountry.dialCode} ${selectedCountry.name}",
            modifier = modifier
                .expandable(menuLabel = label, onExpandedChange = onExpandedChange),
            color = textColorDark,
            fontSize = 12.sp,
            lineHeight = 13.3.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
        )
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = "Spinner arrow",
            modifier = Modifier
                .rotate(
                    if (expanded) 180f else 0f
                )
        )
    }
    /*OutlinedTextField(
        value = if (selectedCountry == null) "" else "${selectedCountry.dialCode} ${selectedCountry.name}",
        onValueChange = {},
        modifier = modifier
            .expandable(menuLabel = label, onExpandedChange = onExpandedChange),
        readOnly = true,
        isError = isError,
        label = {
            Text(text = label)
        },
        colors = colors,
        shape = shape,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Spinner arrow",
                modifier = Modifier
                    .rotate(
                        if (expanded) 180f else 0f
                    )
            )
        }
    )*/
}

@Composable
fun Modifier.expandable(
    onExpandedChange: () -> Unit,
    menuLabel: String
) = pointerInput(Unit) {
    forEachGesture {
        coroutineScope {
            awaitPointerEventScope {
                var event: PointerEvent
                do {
                    event = awaitPointerEvent(PointerEventPass.Initial)
                } while (!event.changes.all {
                        it.changedToUp()
                    })
                onExpandedChange.invoke()
            }
        }
    }
}.semantics {
    contentDescription = menuLabel
    onClick {
        onExpandedChange()
        true
    }
}