/*
 * Copyright 2024 Movemate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.calebk.shipments.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    showSearchBar: Boolean = false,
    navigateBackHome: () -> Unit = {},
    onReadyToSearch: (Boolean) -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AnimatedVisibility(visible = showSearchBar) {
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = {
                    navigateBackHome()
                    onReadyToSearch(false)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OutlinedTextField(
                placeholder = { Text("Enter the receipt number …") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        modifier = Modifier.minimumInteractiveComponentSize(),
                        tint = Color.Blue.copy(alpha = 0.5f),
                    )
                },
                modifier = Modifier
                    .focusRequester(FocusRequester.Default)
                    .onFocusChanged { onReadyToSearch(it.isFocused) }
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .weight(1f)
                    .minimumInteractiveComponentSize()
                    .testTag("Search Bar"),
                value = searchQuery,

                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search,
                ),
                onValueChange = onSearchQueryChange,
                trailingIcon = {
                    Surface(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(32.dp),
                        color = Color(0xFFFF9800),

                    ) {
                        IconButton(
                            modifier = Modifier,
                            onClick = { onSearchQueryChange("") },
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.CreditCard,
                                contentDescription = "Clear",
                                tint = Color.White,
                            )
                        }
                    }
                },
                textStyle = TextStyle(
                    color = Color.Black,
                ),
            )
        }
    }
}
