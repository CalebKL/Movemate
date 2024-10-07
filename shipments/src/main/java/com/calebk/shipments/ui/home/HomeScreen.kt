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
package com.calebk.shipments.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.calebk.shipments.R
import com.calebk.shipments.models.ShipmentItems
import com.calebk.shipments.ui.composables.AnimatedTopBar
import com.calebk.shipments.ui.composables.AnimatedVehicleCards
import com.calebk.shipments.ui.composables.BottomBarWithTopSlider
import com.calebk.shipments.ui.composables.NothingHere
import com.calebk.shipments.ui.composables.SearchableItems
import com.calebk.shipments.ui.composables.ShipmentContent
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navigateToShipment: () -> Unit = {},
    navigateToCalculateScreen: () -> Unit = {},
    shipmentList: List<ShipmentItems>,
    onSearch: (String) -> Unit,
    loading: Boolean,
    navigateBackHome: () -> Unit,
) {
    var searchQuery by rememberDebouncedSearchState()
    var showSearchContent by rememberSaveable { mutableStateOf(false) }
    var shouldAnimate by remember { mutableStateOf(false) }

    LaunchedEffect(showSearchContent) {
        shouldAnimate = true
    }

    val offsetY by animateIntAsState(
        targetValue = if (shouldAnimate) 0 else -200,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        label = "offsetY",
    )

    val alpha by animateFloatAsState(
        targetValue = if (shouldAnimate) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        label = "alpha",
    )

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = Color.White,
        topBar = {
            AnimatedTopBar(
                modifier = Modifier
                    .alpha(alpha)
                    .offset(y = offsetY.dp),
                showSearchContent = showSearchContent,
                searchQuery = searchQuery,
                onSearchQueryChange = {
                    searchQuery = it
                    onSearch(searchQuery)
                },
                navigateBackHome = {
                    showSearchContent = false
                    navigateBackHome()
                },
                backgroundColor = Color(0xFF5f57bc),
                onReadyToSearch = { focused ->
                    if (focused) {
                        showSearchContent = true
                    }
                },

            )
        },
        bottomBar = {
            AnimatedVisibility(!showSearchContent) {
                BottomBarWithTopSlider(
                    navigateToCalculateScreen = navigateToCalculateScreen,
                    navigateToShipment = navigateToShipment,
                )
            }
        },
        content = {
            AnimatedVisibility(
                visible = showSearchContent,
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
                Column(
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    if (loading && shipmentList.isEmpty()) {
                        CircularProgressIndicator()
                    } else if (shipmentList.isEmpty()) {
                        NothingHere()
                    } else {
                        ElevatedCard(
                            modifier = Modifier
                                .padding(it)
                                .background(Color.White)
                                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                            colors = CardColors(
                                contentColor = Color(0xFF858585),
                                containerColor = Color.White,
                                disabledContentColor = Color.Black,
                                disabledContainerColor = Color.Black,
                            ),
                        ) {
                            LazyColumn {
                                items(shipmentList) { item ->
                                    SearchableItems(
                                        shipmentItemName = item.name,
                                        shipmentItemId = item.id,
                                        shippedFrom = item.shippedFrom,
                                        deliveryAddress = item.deliveryAddress,
                                    )
                                }
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = !showSearchContent,
            ) {
                LazyColumn(
                    Modifier.padding(it),
                ) {
                    item {
                        Spacer(Modifier.height(16.dp))
                        AnimatedVisibility(
                            visible = shouldAnimate,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp),
                                text = stringResource(R.string.tracking),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = shouldAnimate,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            ShipmentContent(
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(start = 12.dp, end = 12.dp),
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                    }
                    item {
                        AnimatedVisibility(
                            visible = shouldAnimate,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 12.dp),
                                text = stringResource(R.string.available_vehicles),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                    item {
                        AnimatedVehicleCards(shouldAnimate)
                    }
                }
            }
        },

    )
}

@Composable
fun rememberDebouncedSearchState(initialValue: String = "", debounceTimeout: Long = 300L): MutableState<String> {
    val searchQuery = remember { mutableStateOf(initialValue) }
    val debouncedSearchQuery = remember { mutableStateOf(initialValue) }

    LaunchedEffect(searchQuery.value) {
        delay(debounceTimeout)
        debouncedSearchQuery.value = searchQuery.value
    }

    return searchQuery
}
