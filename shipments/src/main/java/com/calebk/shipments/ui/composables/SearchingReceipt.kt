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

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchingReceipt(
    modifier: Modifier = Modifier,
    onSearchQueryChange: (String) -> Unit = {},
    searchQuery: String,
    navigateBackHome: () -> Unit,
    onReadyToSearch: (Boolean) -> Unit,
    showSearchBar: Boolean,
) {
    Column(
        modifier = modifier,
    ) {
        SearchBar(
            onSearchQueryChange = onSearchQueryChange,
            searchQuery = searchQuery,
            onReadyToSearch = { onReadyToSearch(true) },
            showSearchBar = showSearchBar,
            navigateBackHome = navigateBackHome,
        )
    }
}
