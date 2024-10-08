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

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun AnimatedTopBar(
    showSearchContent: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    navigateBackHome: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onReadyToSearch: (Boolean) -> Unit = {},
) {
    val offsetY by animateFloatAsState(
        targetValue = if (showSearchContent) 0f else 1f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing,
        ),
        label = "offsetY",
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
    ) {
        AnimatedContent(
            targetState = showSearchContent,
            transitionSpec = {
                fadeIn(animationSpec = tween(500, easing = LinearOutSlowInEasing)) togetherWith
                    fadeOut(animationSpec = tween(500, easing = LinearOutSlowInEasing))
            },
            label = "TopBar Animation",
        ) { isSearchVisible ->
            if (isSearchVisible) {
                SearchingReceipt(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(top = 40.dp, bottom = 20.dp)
                        .padding(horizontal = 16.dp)
                        .offset { IntOffset(0, (offsetY * 200f).roundToInt()) },
                    searchQuery = searchQuery,
                    onSearchQueryChange = onSearchQueryChange,
                    navigateBackHome = navigateBackHome,
                    onReadyToSearch = onReadyToSearch,
                    showSearchBar = showSearchContent,
                )
            } else {
                HeaderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(top = 40.dp)
                        .padding(horizontal = 16.dp),
                    searchQuery = searchQuery,
                    onSearchQueryChange = onSearchQueryChange,
                    onReadyToSearch = onReadyToSearch,
                )
            }
        }
    }
}
