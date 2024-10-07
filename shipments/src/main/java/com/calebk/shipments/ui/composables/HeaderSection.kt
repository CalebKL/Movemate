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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.calebk.shipments.R

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit = {},
    onReadyToSearch: (Boolean) -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier.padding(top = 12.dp),
                shape = CircleShape,
                onClick = {},
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pic),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1F),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        modifier = Modifier.size(12.dp),
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.NearMe,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = stringResource(R.string.your_location),
                        color = Color.White,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = stringResource(R.string.wertheimer_illinois),
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(
                        modifier = Modifier.size(12.dp),
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }
            }
            Surface(
                modifier = Modifier.clip(CircleShape),
            ) {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = { },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.NotificationsNone,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            onReadyToSearch = onReadyToSearch,
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}
