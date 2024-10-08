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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AvTimer
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calebk.shipments.R
import com.calebk.shipments.models.Category

@Composable
fun ShipmentHistoryCard(modifier: Modifier = Modifier, progress: Category, trackingNumber: String, shippedFrom: String, amount: String) {
    OutlinedCard(
        modifier = modifier,
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Black,
        ),
    ) {
        Row(
            modifier = Modifier.padding(top = 12.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, bottom = 12.dp),
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.LightGray.copy(alpha = 0.2F),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        when (progress) {
                            Category.PENDING -> {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Rounded.History,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    progress.categoryName,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }

                            Category.COMPLETED -> {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Rounded.AvTimer,
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(progress.categoryName, fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                            }

                            Category.IN_PROGRESS -> {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Rounded.Repeat,
                                    contentDescription = null,
                                    tint = Color.Green,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = progress.categoryName,
                                    color = Color.Green,
                                    fontSize = 14.sp,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }

                            Category.CANCELLED -> {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Rounded.AvTimer,
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(progress.categoryName, fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.arriving_today),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.your_delivery_from_is_arriving_today, trackingNumber, shippedFrom),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = amount,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        Modifier
                            .size(6.dp)
                            .align(Alignment.CenterVertically)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = stringResource(R.string.sep_20_2023), style = MaterialTheme.typography.bodyMedium)
                }
            }
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.gray_box),
                contentDescription = null,
            )
        }
    }
}
