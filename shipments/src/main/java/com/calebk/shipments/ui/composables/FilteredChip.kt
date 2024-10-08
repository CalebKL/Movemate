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
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.calebk.shipments.ui.composables.Dimensions.CHIP_BORDER_WIDTH
import com.calebk.shipments.ui.composables.Dimensions.CHIP_CORNER_RADIUS
import com.calebk.shipments.ui.composables.Dimensions.CHIP_SPACING
import com.calebk.shipments.ui.composables.Dimensions.CHIP_VERTICAL_SPACING
import com.calebk.shipments.ui.composables.Dimensions.ICON_TEXT_SPACING

@Composable
fun FilterChipsSection(modifier: Modifier = Modifier) {
    var shouldAnimateIn by remember { mutableStateOf(false) }

    // Trigger animation after composition
    LaunchedEffect(Unit) {
        shouldAnimateIn = true
    }

    val filterCategories = listOf(
        FilterCategory.DOCUMENTS,
        FilterCategory.GLASS,
        FilterCategory.LIQUID,
        FilterCategory.FOOD,
        FilterCategory.ELECTRONICS,
        FilterCategory.PRODUCTS,
        FilterCategory.MISCELLANEOUS,
    )

    AnimatedFilterChips(
        isVisible = shouldAnimateIn,
        categories = filterCategories,
        modifier = modifier,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AnimatedFilterChips(modifier: Modifier, isVisible: Boolean, categories: List<FilterCategory>) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth / 4 },
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION,
                easing = LinearEasing,
            ),
        ),
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(CHIP_SPACING),
            verticalArrangement = Arrangement.spacedBy(CHIP_VERTICAL_SPACING),
        ) {
            categories.forEach { category ->
                FilterChip(category = category)
            }
        }
    }
}

@Composable
private fun FilterChip(category: FilterCategory) {
    var isSelected by remember { mutableStateOf(false) }
    SelectableChip(
        isSelected = isSelected,
        onSelectionChanged = { isSelected = it },
        category = category,
    )
}

@Composable
private fun SelectableChip(isSelected: Boolean, onSelectionChanged: (Boolean) -> Unit, category: FilterCategory) {
    OutlinedButton(
        onClick = { onSelectionChanged(!isSelected) },
        border = BorderStroke(
            width = CHIP_BORDER_WIDTH,
            color = MaterialTheme.colorScheme.secondary,
        ),
        shape = RoundedCornerShape(CHIP_CORNER_RADIUS),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color.Black else Color.Transparent,
        ),
    ) {
        ChipContent(
            isSelected = isSelected,
            category = category,
        )
    }
}

@Composable
private fun ChipContent(isSelected: Boolean, category: FilterCategory) {
    Row {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
            )
            Spacer(modifier = Modifier.width(ICON_TEXT_SPACING))
        }
        Text(
            text = category.filterName,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) Color.White else Color.Black,
        )
    }
}

enum class FilterCategory(val filterName: String) {
    DOCUMENTS("Documents"),
    GLASS("Glass"),
    LIQUID("Liquid"),
    FOOD("Food"),
    ELECTRONICS("Electronic"),
    PRODUCTS("Product"),
    MISCELLANEOUS("Others"),
}

private object Dimensions {
    val CHIP_SPACING = 4.dp
    val CHIP_VERTICAL_SPACING = 1.dp
    val CHIP_BORDER_WIDTH = 1.dp
    val CHIP_CORNER_RADIUS = 4.dp
    val ICON_TEXT_SPACING = 4.dp
}

private const val ANIMATION_DURATION = 300
