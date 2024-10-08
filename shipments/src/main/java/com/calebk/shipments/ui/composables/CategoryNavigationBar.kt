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

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calebk.shipments.R
import com.calebk.shipments.models.Category
import com.calebk.shipments.models.ShipmentItems

@Composable
fun ShipmentHistoryScreen(historyItems: List<ShipmentItems>, loading: Boolean, modifier: Modifier, shouldAnimateUpOnFirstLaunch: Boolean) {
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var filteredHistory by remember { mutableStateOf(historyItems) }
    var shouldAnimate by remember { mutableStateOf(true) }
    val rowOffset = remember { Animatable(100f) }

    LaunchedEffect(Unit) {
        rowOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        )
    }

    Column(
        modifier = modifier,
    ) {
        CategoryNavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 8.dp)
                .offset(x = rowOffset.value.dp)
                .testTag("Navigation bar Items"),
            historyItems = historyItems,
            onCategorySelected = { category ->
                selectedCategory = category
                filteredHistory = if (category == null) {
                    historyItems
                } else {
                    historyItems.filter { it.category == category }
                }
                shouldAnimate = true
            },
        )
        AnimatedVisibility(
            visible = shouldAnimateUpOnFirstLaunch,
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
            AnimatedContent(
                targetState = Pair(selectedCategory, filteredHistory),
                transitionSpec = {
                    (
                        slideInVertically(
                            initialOffsetY = { fullHeight -> fullHeight },
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = EaseOutQuart,
                            ),
                        ) + fadeIn(animationSpec = tween(300))
                        ).togetherWith(
                        slideOutVertically(
                            targetOffsetY = { fullHeight -> -fullHeight },
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = EaseInQuart,
                            ),
                        ) + fadeOut(animationSpec = tween(300)),
                    )
                },
                label = "Selection Category",
            ) { (_, currentFilteredHistory) ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(R.string.shipments),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    when {
                        loading && currentFilteredHistory.isEmpty() -> {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize(),
                                )
                            }
                        }

                        currentFilteredHistory.isEmpty() -> {
                            item {
                                NothingHere()
                            }
                        }

                        else -> {
                            items(currentFilteredHistory) { item ->
                                ShipmentHistoryCard(
                                    modifier = Modifier.fillMaxWidth(),
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
        }
    }
}

@Composable
fun CategoryNavigationBar(modifier: Modifier = Modifier, historyItems: List<ShipmentItems>, onCategorySelected: (Category?) -> Unit) {
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    Row(
        modifier = modifier,
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
        modifier = Modifier
            .clickable(onClick = onCategorySelected),
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
        color = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.surfaceTint,
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
            .background(MaterialTheme.colorScheme.tertiary)
            .size(width = 100.dp, height = 4.dp),
    )
}
