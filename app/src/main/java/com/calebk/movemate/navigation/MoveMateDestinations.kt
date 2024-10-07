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
package com.calebk.movemate.navigation

interface MoveMateDestinations {

    val route: String

    object HomeScreen : MoveMateDestinations {
        override val route = "com.calebk.movemate.homescreen"
    }
    object CalculateScreen : MoveMateDestinations {
        override val route = "com.calebk.movemate.calculatescreen"
    }

    object ShipmentScreen : MoveMateDestinations {
        override val route = "com.calebk.movemate.shipmentscreen"
    }
    object ProfileScreen : MoveMateDestinations {
        override val route = "com.calebk.movemate.profilescreen"
    }
    object PricingScreen : MoveMateDestinations {
        override val route = "com.calebk.movemate.pricingscreen"
    }
}
