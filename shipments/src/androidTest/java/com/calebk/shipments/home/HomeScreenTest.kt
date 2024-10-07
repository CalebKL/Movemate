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
package com.calebk.shipments.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.calebk.shipments.ui.home.HomeScreen
import com.calebk.shipments.utils.FakeGeneratorUtil.fakeShipmentItems
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHomeScreen_initialState_display() {
        composeTestRule.setContent {
            HomeScreen(
                shipmentList = fakeShipmentItems,
                onSearch = { },
                loading = false,
                navigateToShipment = {},
                navigateToCalculateScreen = {},
                navigateBackHome = {},
            )
        }
        composeTestRule
            .onNodeWithText("Tracking")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Shipment Number")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("Shipment Content")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Available Vehicles")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("vehicles row")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Home").isDisplayed()
        composeTestRule.onNodeWithContentDescription("Calculate").isDisplayed()
        composeTestRule.onNodeWithContentDescription("Shipment").isDisplayed()
        composeTestRule.onNodeWithContentDescription("Profile").isDisplayed()
    }

    @Test
    fun testHomeScreen_searchBarClick_navigatesToShipmentItemsList() {
        composeTestRule.setContent {
            HomeScreen(
                shipmentList = fakeShipmentItems,
                onSearch = { },
                loading = false,
                navigateToShipment = { },
                navigateToCalculateScreen = {},
                navigateBackHome = {},
            )
        }
        composeTestRule.onNodeWithTag("Search Bar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Search Bar").performClick()
        composeTestRule.onNodeWithTag("Searchable Items").assertExists()
    }

    @Test
    fun testSearchBarClick_isAbleToSearch_forShippingItems() {
        composeTestRule.setContent {
            HomeScreen(
                shipmentList = fakeShipmentItems,
                onSearch = { },
                loading = false,
                navigateToShipment = { },
                navigateToCalculateScreen = {},
                navigateBackHome = {},
            )
        }
        composeTestRule.onNodeWithTag("Search Bar").performClick()
        composeTestRule.onNodeWithTag("Search Bar").performTextInput("231")
        composeTestRule.onNodeWithText("#NEJ20089934122231").isDisplayed()
    }

    @Test
    fun testNavigationTo_CalculateScreen_and_ShipmentScreen() {
        composeTestRule.setContent {
            HomeScreen(
                shipmentList = fakeShipmentItems,
                onSearch = { },
                loading = false,
                navigateToShipment = { },
                navigateToCalculateScreen = {},
                navigateBackHome = {},
            )
        }
        composeTestRule.onNodeWithContentDescription("Calculate").performClick()
        composeTestRule.onNodeWithText("Calculate").isDisplayed()
        composeTestRule.onNodeWithContentDescription("Shipment").performClick()
        composeTestRule.onNodeWithText("Shipment History").isDisplayed()
    }
}
