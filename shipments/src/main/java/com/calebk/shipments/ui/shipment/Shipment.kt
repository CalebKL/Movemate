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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.calebk.shipments.R
import com.calebk.shipments.models.ShipmentItems
import com.calebk.shipments.ui.composables.ShipmentHistoryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentScreen(shipmentHistory: List<ShipmentItems>, loading: Boolean, navigateBackHome: () -> Unit) {
    var shouldAnimateOnFirstLaunch by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        shouldAnimateOnFirstLaunch = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    scrolledContainerColor = Color.White,
                ),
                title = {
                    AnimatedVisibility(
                        visible = shouldAnimateOnFirstLaunch,
                        enter = slideInVertically(
                            initialOffsetY = { it },
                            animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                        ),
                    ) {
                        Text(
                            text = stringResource(R.string.shipment_history),
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                        )
                    }
                },
                navigationIcon = {
                    AnimatedVisibility(shouldAnimateOnFirstLaunch) {
                        IconButton(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .size(20.dp)
                                .testTag("Back Button"),
                            onClick = navigateBackHome,
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                tint = Color.White,
                                contentDescription = stringResource(R.string.navigate_back),
                            )
                        }
                    }
                },

            )
        },
        content = {
            ShipmentHistoryScreen(
                modifier = Modifier.padding(it).background(Color.White),
                historyItems = shipmentHistory,
                loading = loading,
                shouldAnimateUpOnFirstLaunch = shouldAnimateOnFirstLaunch,
            )
        },
    )
}
