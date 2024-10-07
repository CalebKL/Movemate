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
package com.calebk.shipments.ui.shipment

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.calebk.shipments.R
import com.calebk.shipments.models.ShipmentItems
import com.calebk.shipments.ui.composables.NothingHere
import com.calebk.shipments.ui.composables.ShipmentHeader
import com.calebk.shipments.ui.composables.ShipmentHistoryCard

@Composable
fun ShipmentScreen(shipmentHistory: List<ShipmentItems>, loading: Boolean, navigateBackHome: () -> Unit) {
    var filteredHistory by remember { mutableStateOf(shipmentHistory) }

    var shouldAnimate by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        shouldAnimate = true
    }

    Scaffold(
        topBar = {
            ShipmentHeader(
                historyItems = shipmentHistory,
                onFilteredItemsChanged = { filtered ->
                    filteredHistory = filtered
                },
                navigateBackHome = navigateBackHome,
            )
        },
        content = {
            AnimatedVisibility(
                visible = shouldAnimate,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = EaseOutQuart,
                    ),
                ) + fadeIn(animationSpec = tween(durationMillis = 500)),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = EaseInQuart,
                    ),
                ) + fadeOut(animationSpec = tween(durationMillis = 500)),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .padding(start = 12.dp, end = 12.dp),
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(R.string.shipments),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                    when {
                        loading && shipmentHistory.isEmpty() -> {
                            item {
                                CircularProgressIndicator()
                            }
                        }
                        filteredHistory.isEmpty() -> {
                            item {
                                NothingHere()
                            }
                        }
                        else -> {
                            items(filteredHistory) { item ->
                                ShipmentHistoryCard(
                                    progress = item.category,
                                    trackingNumber = item.id,
                                    shippedFrom = item.shippedFrom,
                                    amount = item.amount,
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
            }
        },
    )
}
