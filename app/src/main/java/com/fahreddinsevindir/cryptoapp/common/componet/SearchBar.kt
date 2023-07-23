package com.fahreddinsevindir.cryptoapp.common.componet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.fahreddinsevindir.cryptoapp.ui.theme.Orj

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    border: BorderStroke = BorderStroke((0.5f).dp, Orj),
    shape: Shape = RoundedCornerShape(32.dp),
    onClearClick: () -> Unit = {},
) {
    var showClearButton by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.padding(12.dp),
        border = border,
        shape = shape
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                modifier = Modifier.padding(start = 12.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurface
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .background(Color.Transparent)
                    .onFocusChanged { focusState ->
                        showClearButton = (focusState.isFocused || searchText.isNotBlank())
                    },
                value = searchText,
                onValueChange = onSearchTextChanged,
                placeholder = {
                    Text(
                        text = placeholderText,
                        modifier = Modifier.wrapContentHeight(),
                        color = Color.Black
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = showClearButton,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(onClick = {
                            onClearClick()
                            focusManager.clearFocus()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                },
                maxLines = 1,
                singleLine = true
            )
        }
    }
}