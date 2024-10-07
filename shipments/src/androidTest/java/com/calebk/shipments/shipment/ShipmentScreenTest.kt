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
package com.calebk.shipments.shipment

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.calebk.shipments.ui.shipment.ShipmentScreen
import com.calebk.shipments.utils.FakeGeneratorUtil.fakeShipmentItems
import org.junit.Rule
import org.junit.Test

class ShipmentScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testShipmentScreen_initialState_display() {
        composeTestRule.setContent {
            ShipmentScreen(
                shipmentHistory = fakeShipmentItems,
                loading = false,
                navigateBackHome = {},
            )
        }
        composeTestRule
            .onNodeWithText("Shipment History")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("Navigation bar Items")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Shipments")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("ShipmentHistoryCard").assertIsDisplayed()
    }

    @Test
    fun testBackButton_Navigates_Back_toHomePage() {
        composeTestRule.setContent {
            ShipmentScreen(
                shipmentHistory = fakeShipmentItems,
                loading = false,
                navigateBackHome = {},
            )
        }
        composeTestRule
            .onNodeWithTag("Back Button")
            .performClick()
        composeTestRule.onNodeWithContentDescription("Search Bar").isDisplayed()
    }
}
