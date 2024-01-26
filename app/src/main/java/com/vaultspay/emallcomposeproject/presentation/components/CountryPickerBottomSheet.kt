package com.vaultspay.emallcomposeproject.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vaultspay.emallcomposeproject.domain.models.Country
import com.vaultspay.emallcomposeproject.utils.countryList
import com.vaultspay.emallcomposeproject.utils.localeToEmoji
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CountryPickerBottomSheet(
    //title: @Composable () -> Unit,
    show: Boolean,
    countries: MutableList<Country?>,
    onItemSelected: (country: Country?) -> Unit,
    onDismissRequest: () -> Unit,
    //content: @Composable () -> Unit
) {
    val context = LocalContext.current
    //val countries = remember { countryList(context) }
    var selectedCountry by remember { mutableStateOf(countries[0]) }
    //val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val modalBottomSheetState = androidx.compose.material3.rememberModalBottomSheetState()

    LaunchedEffect(key1 = show) {
        if (show) modalBottomSheetState.show() else modalBottomSheetState.hide()
    }

    /*LaunchedEffect(key1 = modalBottomSheetState.currentValue) {
        if (modalBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            onDismissRequest()
        }
    }*/

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(countries.size) { index ->
                Row(
                    modifier = Modifier
                        .clickable {
                            selectedCountry = countries[index]
                            onItemSelected(selectedCountry)
                        }
                        .padding(15.dp)
                ) {
                    Text(
                        text = localeToEmoji(countryCode = countries[index]?.code ?: "")
                    )

                    Text(
                        text = countries[index]?.name ?: "",
                        modifier = Modifier
                            .padding(start = 6.dp)
                        //.weight(2f)
                    )

                    Text(
                        text = " (${countries[index]?.dialCode})",
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }

                Divider(color = Color.LightGray, thickness = 0.5.dp)
            }
        }
    }

    /* ModalBottomSheetLayout(
         sheetContent = {
             title()
             LazyColumn(
                 contentPadding = PaddingValues(16.dp)
             ) {
                 items(countries.size) { index ->
                     Row(
                         modifier = Modifier
                             .clickable {
                                 selectedCountry = countries[index]
                                 onItemSelected(selectedCountry)
                             }
                             .padding(10.dp)
                     ) {
                         Text(
                             text = localeToEmoji(countryCode = countries[index]?.code ?: "")
                         )

                         Text(
                             text = countries[index]?.name ?: "",
                             modifier = Modifier
                                 .padding(start = 6.dp)
                                 .weight(2f)
                         )

                         Text(
                             text = countries[index]?.dialCode ?: "",
                             modifier = Modifier.padding(start = 6.dp)
                         )
                     }

                     Divider(color = Color.LightGray, thickness = 0.5.dp)
                 }
             }
         },
         sheetState = modalBottomSheetState,
         sheetShape = RoundedCornerShape(
             topStart = 20.dp, topEnd = 20.dp
         )
     ) {
         content()
     }*/
}