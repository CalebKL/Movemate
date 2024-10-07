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

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.calebk.shipments.models.vehicleItems

@Composable
fun AnimatedVehicleCards(shouldAnimate: Boolean) {
    LazyRow(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(vehicleItems) { item ->
            val animatedOffset by animateFloatAsState(
                targetValue = if (shouldAnimate) 0f else 1f,
                animationSpec = tween(
                    durationMillis = 500,
                    delayMillis = vehicleItems.indexOf(item) * 50,
                    easing = FastOutSlowInEasing,
                ),
                label = "offsetAnimation",
            )
            Box(
                modifier = Modifier.offset(x = (animatedOffset * 300).dp),
            ) {
                VehicleCard(
                    modifier = Modifier.height(170.dp),
                    title = item.title,
                    subTitle = item.subtitle,
                    drawableResource = item.drawableRes,
                )
            }
        }
    }
}
