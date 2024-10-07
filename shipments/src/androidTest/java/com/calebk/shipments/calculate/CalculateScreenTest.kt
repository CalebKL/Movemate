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
package com.calebk.shipments.calculate

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.calebk.shipments.ui.calculate.CalculateScreen
import org.junit.Rule
import org.junit.Test

class CalculateScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCalculateScreen_initialState_display() {
        composeTestRule.setContent {
            CalculateScreen(
                navigateBackHome = {},
                navigateToPricingPage = {},
            )
        }

        composeTestRule
            .onNodeWithText("Destination")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Packaging")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Sender Location")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Receiver Location")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Approx weight")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Box")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("FilterChipsSection")
            .assertExists()
        composeTestRule
            .onNodeWithTag("Calculate Button")
            .assertIsDisplayed()
    }
}
