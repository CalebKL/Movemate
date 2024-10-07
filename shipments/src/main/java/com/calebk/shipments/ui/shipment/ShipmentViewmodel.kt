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
package com.calebk.shipments.ui.shipment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.calebk.shipments.models.ShipmentItems
import com.calebk.shipments.repo.ShipmentRepo
import com.calebk.shipments.repo.ShipmentRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShipmentViewmodel(private val repo: ShipmentRepo) : ViewModel() {
    private val _historyItems = MutableStateFlow<List<ShipmentItems>>(emptyList())
    val historyItems = _historyItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getHistoryItems()
    }
    private fun getHistoryItems() {
        viewModelScope.launch {
            repo.getShipmentHistory()
                .onStart { _isLoading.update { true } }
                .collect { items ->
                    _isLoading.update { false }
                    _historyItems.update { items }
                }
        }
    }
    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ShipmentViewmodel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return ShipmentViewmodel(
                        repo = ShipmentRepoImpl(),
                    ) as T
                }
                throw IllegalArgumentException("Unknown ViewModel Class")
            }
        }
    }
}
