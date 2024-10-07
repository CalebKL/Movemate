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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.calebk.shipments.models.Category
import com.calebk.shipments.models.ShipmentItems

@Composable
fun CategoryNavigationBar(historyItems: List<ShipmentItems>, onCategorySelected: (Category?) -> List<ShipmentItems>) {
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .background(Color(0xFF5f57bc))
            .padding(horizontal = 8.dp),
    ) {
        CategoryTab(
            categoryName = "All",
            count = historyItems.size,
            isSelected = selectedCategory == null,
            onCategorySelected = {
                selectedCategory = null
                onCategorySelected(null)
            },
        )
        // Individual category tabs with filtered counts
        Category.entries.forEach { category ->
            val categoryCount = historyItems.count { it.category == category }
            CategoryTab(
                categoryName = category.categoryName,
                count = categoryCount,
                isSelected = selectedCategory == category,
                onCategorySelected = {
                    selectedCategory = category
                    onCategorySelected(category)
                },
            )
        }
    }
}

@Composable
private fun CategoryTab(count: Int, categoryName: String, isSelected: Boolean, onCategorySelected: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onCategorySelected),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CategoryLabel(
            name = categoryName,
            count = count,
            isSelected = isSelected,
        )
        if (isSelected) {
            SelectionIndicator()
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun CategoryLabel(name: String, count: Int, isSelected: Boolean) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f),
        )
        Spacer(modifier = Modifier.width(4.dp))
        CountBadge(
            count = count,
            isSelected = isSelected,
        )
    }
}

@Composable
private fun CountBadge(count: Int, isSelected: Boolean) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color(0xFFCA7800) else Color(0xFF6570AE),
        modifier = Modifier.padding(horizontal = 12.dp),
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
        )
    }
}

@Composable
private fun SelectionIndicator() {
    Spacer(modifier = Modifier.height(8.dp))
    Box(
        modifier = Modifier
            .background(Color(0xFFFF9800))
            .size(width = 100.dp, height = 4.dp),
    )
}
