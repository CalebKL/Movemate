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
package com.calebk.shipments.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.calebk.shipments.models.ShipmentItems
import com.calebk.shipments.repo.ShipmentRepo
import com.calebk.shipments.repo.ShipmentRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewmodel(private val repo: ShipmentRepo) : ViewModel() {
    private val _shipmentItems = MutableStateFlow<List<ShipmentItems>>(emptyList())
    val shipmentItems = _shipmentItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Keep track of the original loaded list before search
    private val originalShipmentItems = mutableListOf<ShipmentItems>()

    init {
        getShipmentItems()
    }
    private fun getShipmentItems() {
        viewModelScope.launch {
            repo.getShipmentList()
                .onStart { _isLoading.update { true } }
                .collect { items -> _shipmentItems.update { items } }
        }
    }

    fun onSearch(query: String) {
        val filteredListFlow = flow {
            if (originalShipmentItems.isEmpty()) {
                originalShipmentItems.addAll(_shipmentItems.value)
            }

            val filteredList = if (query.isNotEmpty()) {
                originalShipmentItems.filter { it.id.contains(query, ignoreCase = true) }
            } else {
                originalShipmentItems
            }

            emit(filteredList)
        }

        viewModelScope.launch {
            filteredListFlow.collect { filteredList ->
                _shipmentItems.value = filteredList
            }
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeViewmodel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return HomeViewmodel(
                        repo = ShipmentRepoImpl(),
                    ) as T
                }
                throw IllegalArgumentException("Unknown ViewModel Class")
            }
        }
    }
}
