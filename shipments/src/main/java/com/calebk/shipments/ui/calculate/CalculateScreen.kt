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
package com.calebk.shipments.ui.calculate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilePresent
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calebk.shipments.R
import com.calebk.shipments.ui.composables.CalculateTopAppBar
import com.calebk.shipments.ui.composables.FilterChipsSection
import com.calebk.shipments.ui.composables.SendingItemCard
import com.calebk.shipments.ui.composables.UserInputFields

@Composable
fun CalculateScreen(navigateToPricingPage: () -> Unit, navigateBackHome: () -> Unit) {
    var shouldAnimate by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        shouldAnimate = true
    }

    Scaffold(
        containerColor = Color(0xFF858585).copy(alpha = 0.1f),
        topBar = {
            CalculateTopAppBar(
                modifier = Modifier
                    .background(Color(0xFF5f57bc)),
                shouldAnimate = shouldAnimate,
                navigateBackHome = navigateBackHome,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                AnimatedVisibility(
                    visible = shouldAnimate,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(400),
                    ),
                ) {
                    Text(
                        modifier = Modifier.padding(start = 24.dp, top = 18.dp),
                        text = stringResource(R.string.destination),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                AnimatedVisibility(
                    visible = shouldAnimate,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(400),
                    ),
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardColors(
                            contentColor = Color(0xFF858585),
                            containerColor = Color.White,
                            disabledContentColor = Color.Black,
                            disabledContainerColor = Color.Black,
                        ),
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                        ) {
                            UserInputFields(
                                modifier = Modifier
                                    .background(color = Color(0xFF858585).copy(alpha = 0.1f))
                                    .padding(18.dp),
                                icon = Icons.Default.UploadFile,
                                placeHolder = stringResource(R.string.sender_location),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            UserInputFields(
                                modifier = Modifier
                                    .background(color = Color(0xFF858585).copy(alpha = 0.1f))
                                    .padding(18.dp),
                                icon = Icons.Default.FilePresent,
                                placeHolder = stringResource(R.string.receiver_location),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            UserInputFields(
                                modifier = Modifier
                                    .background(color = Color(0xFF858585).copy(alpha = 0.1f))
                                    .padding(18.dp),
                                icon = Icons.Default.HourglassTop,
                                placeHolder = stringResource(R.string.approx_weight),
                            )
                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(start = 18.dp),
                    text = stringResource(R.string.packaging),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 18.dp),
                    text = stringResource(R.string.what_are_you_sending),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF858585),
                )
                SendingItemCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
                Text(
                    modifier = Modifier.padding(start = 18.dp),
                    text = stringResource(R.string.categories),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 18.dp),
                    text = stringResource(R.string.what_are_you_sending),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF858585),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    FilterChipsSection(
                        modifier = Modifier.padding(start = 12.dp, end = 4.dp).testTag("FilterChipsSection"),
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                            .testTag("Calculate Button"),
                        onClick = {
                            navigateToPricingPage()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                    ) {
                        Text(
                            text = stringResource(R.string.calculate),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        },
    )
}
