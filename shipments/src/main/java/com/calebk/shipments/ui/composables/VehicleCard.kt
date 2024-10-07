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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun VehicleCard(modifier: Modifier = Modifier, title: String, subTitle: String, drawableResource: Int) {
    OutlinedCard(
        modifier = modifier,
        colors = CardColors(
            contentColor = Color(0xFF858585),
            containerColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Black,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = subTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF858585),
            )
            Spacer(Modifier.height(4.dp))
            Image(
                modifier = Modifier.size(130.dp, 150.dp),
                painter = painterResource(id = drawableResource),
                contentDescription = "",
            )
        }
    }
}
