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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.calebk.shipments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateTopAppBar(shouldAnimate: Boolean, modifier: Modifier = Modifier, navigateBackHome: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarColors(
            containerColor = Color(0xFF5f57bc),
            scrolledContainerColor = Color(0xFF5f57bc),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        title = {
            AnimatedVisibility(
                visible = shouldAnimate,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                ),
            ) {
                Text(
                    text = stringResource(R.string.calculate),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
            }
        },
        navigationIcon = {
            AnimatedVisibility(shouldAnimate) {
                IconButton(
                    onClick = {
                        navigateBackHome()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        tint = Color.White,
                        contentDescription = null,
                    )
                }
            }
        },
    )
}
