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
package com.calebk.shipments.utils

import com.calebk.shipments.models.Category
import com.calebk.shipments.models.ShipmentItems

object FakeGeneratorUtil {
    val fakeShipmentItems = listOf(
        ShipmentItems(
            name = "Item 1",
            id = "1",
            shippedFrom = "Origin 1",
            category = Category.CANCELLED,
            deliveryAddress = "Atlanta",
            amount = "450",
        ),
        ShipmentItems(
            name = "Item 1",
            id = "1",
            shippedFrom = "Origin 1",
            category = Category.CANCELLED,
            deliveryAddress = "Atlanta",
            amount = "450",
        ),
    )
}
