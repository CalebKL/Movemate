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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.calebk.shipments.ui.calculate.CalculateScreen
import com.calebk.shipments.ui.home.HomeScreen
import com.calebk.shipments.ui.home.HomeViewmodel
import com.calebk.shipments.ui.pricing.PricingPageScreen
import com.calebk.shipments.ui.shipment.ShipmentScreen
import com.calebk.shipments.ui.shipment.ShipmentViewmodel

@Composable
fun MoveMateNavigation(navController: NavHostController) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = MoveMateDestinations.HomeScreen.route,
    ) {
        composable(route = MoveMateDestinations.HomeScreen.route) {
            val viewModel: HomeViewmodel = viewModel(
                factory =
                HomeViewmodel.provideFactory(),
            )
            val shipmentItems by viewModel.shipmentItems.collectAsStateWithLifecycle()
            val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

            HomeScreen(
                navigateToShipment = { navController.navigate(MoveMateDestinations.ShipmentScreen.route) },
                navigateToCalculateScreen = { navController.navigate(MoveMateDestinations.CalculateScreen.route) },
                shipmentList = shipmentItems,
                onSearch = {
                    viewModel.onSearch(it)
                },
                loading = isLoading,
                navigateBackHome = { navController.navigate(MoveMateDestinations.HomeScreen.route) },
            )
        }
        composable(route = MoveMateDestinations.ShipmentScreen.route) {
            val viewModel: ShipmentViewmodel = viewModel(
                factory =
                ShipmentViewmodel.provideFactory(),
            )
            val historyItems by viewModel.historyItems.collectAsStateWithLifecycle()
            val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

            ShipmentScreen(
                shipmentHistory = historyItems,
                loading = isLoading,
                navigateBackHome = { navController.navigate(MoveMateDestinations.HomeScreen.route) },
            )
        }
        composable(route = MoveMateDestinations.CalculateScreen.route) {
            CalculateScreen(
                navigateToPricingPage = { navController.navigate(MoveMateDestinations.PricingScreen.route) },
                navigateBackHome = { navController.navigate(MoveMateDestinations.HomeScreen.route) },
            )
        }
        composable(route = MoveMateDestinations.ProfileScreen.route) {
        }
        composable(route = MoveMateDestinations.PricingScreen.route) {
            PricingPageScreen(
                navigateBackHome = { navController.navigate(MoveMateDestinations.HomeScreen.route) },
            )
        }
    }
}
