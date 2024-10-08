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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.calebk.shipments.R

@Composable
fun ShipmentContent(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .testTag("Shipment Content"),
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Black,
        ),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            Column(
                modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.shipment_number),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.shipment_identity),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.train),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f),
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.compas),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyMedium,
                            text = stringResource(R.string.sender),
                        )
                        Text(
                            text = stringResource(R.string.atlanta),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,
                        text = stringResource(R.string.time),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 24.dp),
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(10.dp),
                        ) {
                            drawCircle(
                                color = Color.Green,
                                radius = 5.dp.toPx(),
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string._2_day_3_days),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f),
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.compas),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyMedium,
                            text = stringResource(R.string.receiver),
                        )
                        Text(
                            text = stringResource(R.string.chicago),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(end = 12.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,
                        text = stringResource(R.string.status),
                    )
                    Text(
                        text = stringResource(R.string.waiting_to_collect),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = Modifier,
                    onClick = { },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color(0xFFFF9800),
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.add_stop),
                    color = Color(0xFFFF9800),
                )
            }
        }
    }
}
