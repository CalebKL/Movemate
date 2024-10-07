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
package com.calebk.shipments.repo

import com.calebk.shipments.models.Category
import com.calebk.shipments.models.ShipmentItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShipmentRepoImpl : ShipmentRepo {
    override fun getShipmentHistory(): Flow<List<ShipmentItems>> = flow {
        val data = listOf(
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.PENDING,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.PENDING,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.PENDING,
                amount = "$650 USD",
            ),

            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.COMPLETED,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.COMPLETED,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.COMPLETED,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.COMPLETED,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.COMPLETED,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ68957487535898",
                name = "Macbook Pro M2",
                shippedFrom = "Atlanta",
                deliveryAddress = "Morocco",
                category = Category.PENDING,
                amount = "$650 USD",
            ),
        )
        emit(data)
    }

    override fun getShipmentList(): Flow<List<ShipmentItems>> = flow {
        val data = listOf(
            ShipmentItems(
                id = "#NE43857340857904",
                name = "Macbook Pro M2",
                shippedFrom = "Paris",
                deliveryAddress = "Morocco",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ20089934122231",
                name = "SUMMER linen Jacket",
                shippedFrom = "Barcelona",
                deliveryAddress = "Paris",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",

            ),
            ShipmentItems(
                id = "#NEJ35870264978659",
                name = "Tapered-fit jeans AW",
                shippedFrom = "Colombia",
                deliveryAddress = "Paris",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",
            ),
            ShipmentItems(
                id = "#NEJ35870264978659",
                name = "Slim fit jeans AW",
                shippedFrom = "Bogota",
                deliveryAddress = "Dhaka",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",

            ),
            ShipmentItems(
                id = "#NEJ23481570754963",
                name = "Office setup Desk",
                shippedFrom = "France",
                deliveryAddress = "German",
                category = Category.IN_PROGRESS,
                amount = "$650 USD",
            ),
        )
        emit(data)
    }
}
