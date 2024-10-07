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

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.calebk.shipments.models.MenuItemData

@Composable
fun BottomBarWithTopSlider(navigateToCalculateScreen: () -> Unit, navigateToShipment: () -> Unit) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val menuItems = listOf(
        MenuItemData("Home", Icons.Outlined.Home, onClick = {}),
        MenuItemData("Calculate", Icons.Outlined.Calculate, onClick = navigateToCalculateScreen),
        MenuItemData("Shipment", Icons.Outlined.History, onClick = navigateToShipment),
        MenuItemData("Profile", Icons.Outlined.Person, onClick = {}),
    )

    val itemWidth = 100.dp

    val sliderOffset by animateDpAsState(targetValue = selectedIndex * itemWidth, label = "slider offset")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Box(
            modifier = Modifier
                .offset(x = sliderOffset)
                .width(itemWidth)
                .height(4.dp)
                .background(Color(0xFF5f57bc))
                .padding(bottom = 8.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            menuItems.forEachIndexed { index, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(itemWidth)
                        .clickable {
                            selectedIndex = index
                            item.onClick()
                        },
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp),
                        tint = if (selectedIndex == index) {
                            Color(0xFF5f57bc)
                        } else {
                            Color.Gray
                        },
                    )
                    Text(text = item.title, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}
