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
package com.calebk.shipments.ui.pricing

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.calebk.shipments.R

@Composable
fun PricingPageScreen(navigateBackHome: () -> Unit) {
    val animationState = remember {
        MutableTransitionState(AnimationState.Initial).apply {
            targetState = AnimationState.Animated
        }
    }

    val transition = rememberTransition(animationState, label = "counterAnimation")
    val boxSlideOffset by transition.animateFloat(
        transitionSpec = {
            tween(1000, easing = LinearOutSlowInEasing)
        },
        label = "boxOffset",
    ) { state ->
        when (state) {
            AnimationState.Initial -> 1000f
            AnimationState.Animated -> 0f
        }
    }

    val textFadeAlpha by transition.animateFloat(
        transitionSpec = {
            tween(1000, easing = LinearOutSlowInEasing)
        },
        label = "textAlpha",
    ) { state ->
        when (state) {
            AnimationState.Initial -> 0f
            AnimationState.Animated -> 1f
        }
    }

    var animationTriggered by remember { mutableStateOf(false) }
    val initialAmount = 1100
    val targetAmount = 1460

    val animatedCount by animateIntAsState(
        targetValue = if (animationTriggered) targetAmount else initialAmount,
        animationSpec = tween(
            durationMillis = 1300,
            easing = LinearEasing,
        ),
        label = "countAnimation",
    )

    LaunchedEffect(textFadeAlpha) {
        if (textFadeAlpha >= 1f) {
            animationTriggered = true
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .height(90.dp)
                    .width(250.dp)
                    .offset(y = boxSlideOffset.dp),
                painter = painterResource(R.drawable.movemate),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .offset(y = boxSlideOffset.dp),
                painter = painterResource(R.drawable.gray_box),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .alpha(textFadeAlpha)
                    .offset(y = boxSlideOffset.dp),
                text = stringResource(R.string.total_estimated_amount),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .alpha(textFadeAlpha)
                    .offset(y = boxSlideOffset.dp),
                text = "$ $animatedCount USD",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Green,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .alpha(textFadeAlpha)
                    .padding(start = 12.dp, end = 12.dp)
                    .offset(y = boxSlideOffset.dp),
                text = stringResource(R.string.amount_may_vary_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .alpha(textFadeAlpha)
                    .offset(y = boxSlideOffset.dp)
                    .testTag("Back Home"),
                onClick = {
                    navigateBackHome()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
            ) {
                Text(
                    text = stringResource(R.string.back_home),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
