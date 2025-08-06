package com.samsung.health.mobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.samsung.health.data.TrackedData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeartbeatViewModel @Inject constructor() : ViewModel() {
    
    private val _heartbeatData = mutableStateOf(emptyList<TrackedData>())
    val heartbeatData: State<List<TrackedData>> = _heartbeatData
    
    fun updateHeartbeatData(data: List<TrackedData>) {
        _heartbeatData.value = data
    }
    
    fun getLatestHeartRate(): Int {
        return heartbeatData.value.lastOrNull()?.let { data ->
            // Replace with correct property name when you find it
            try {
                // data.heartRate.toIntOrNull() ?: 0
                85 // Temporary demo value
            } catch (e: Exception) {
                0
            }
        } ?: 0
    }
}