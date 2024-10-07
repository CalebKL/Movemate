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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.calebk.shipments.models.ShipmentItems

@Composable
fun AnimatedCategoryNavigationBar(historyItems: List<ShipmentItems>, onFilteredItemsChanged: (List<ShipmentItems>) -> Unit) {
    val tabRowOffset = remember { Animatable(-1000f) }

    LaunchedEffect(Unit) {
        tabRowOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing,
            ),
        )
    }

    Box(
        modifier = Modifier
            .background(Color(0xFF5f57bc))
            .offset(x = tabRowOffset.value.dp),
    ) {
        CategoryNavigationBar(
            historyItems = historyItems,
            onCategorySelected = { category ->
                val filteredItems = when (category) {
                    null -> historyItems
                    else -> historyItems.filter { it.category == category }
                }
                onFilteredItemsChanged(filteredItems)
                filteredItems
            },
        )
    }
}
