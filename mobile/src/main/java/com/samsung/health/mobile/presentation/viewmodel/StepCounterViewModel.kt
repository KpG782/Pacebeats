package com.samsung.health.mobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StepCounterUiState(
    val isTracking: Boolean = false,
    val currentSteps: Int = 0,
    val totalSessionSteps: Int = 0,
    val initialStepCount: Float = 0f,
    val cadence: Float = 0f,
    val elapsedTime: Long = 0L,
    val startTime: Long = 0L,
    val sensorReporting: Boolean = false,
    val errorMessage: String = ""
)

class StepCounterViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(StepCounterUiState())
    val uiState: StateFlow<StepCounterUiState> = _uiState.asStateFlow()
    
    fun startTracking() {
        val currentTime = System.currentTimeMillis()
        _uiState.value = _uiState.value.copy(
            isTracking = true,
            currentSteps = 0,
            startTime = currentTime,
            initialStepCount = 0f,
            sensorReporting = false,
            errorMessage = "",
            elapsedTime = 0L
        )
        
        // Start elapsed time counter
        startTimeCounter()
    }
    
    fun stopTracking() {
        val finalSteps = _uiState.value.currentSteps
        _uiState.value = _uiState.value.copy(
            isTracking = false,
            totalSessionSteps = finalSteps,
            sensorReporting = false
        )
    }
    
    fun onSensorDataReceived(stepCountValue: Float) {
        val currentState = _uiState.value
        
        if (!currentState.isTracking) return
        
        // Mark sensor as reporting
        if (!currentState.sensorReporting) {
            _uiState.value = currentState.copy(sensorReporting = true)
        }
        
        // Initialize baseline on first reading
        if (currentState.initialStepCount == 0f) {
            _uiState.value = currentState.copy(initialStepCount = stepCountValue)
            return
        }
        
        // Calculate current session steps
        val sessionSteps = (stepCountValue - currentState.initialStepCount).toInt()
        val elapsedMillis = System.currentTimeMillis() - currentState.startTime
        val elapsedMinutes = elapsedMillis / 60000f
        val newCadence = if (elapsedMinutes > 0 && sessionSteps > 0) {
            sessionSteps / elapsedMinutes
        } else 0f
        
        _uiState.value = currentState.copy(
            currentSteps = sessionSteps,
            cadence = newCadence
        )
    }
    
    fun onSensorConnected() {
        _uiState.value = _uiState.value.copy(errorMessage = "")
    }
    
    fun onSensorError(error: String) {
        _uiState.value = _uiState.value.copy(
            errorMessage = error,
            isTracking = false
        )
    }
    
    private fun startTimeCounter() {
        viewModelScope.launch {
            while (_uiState.value.isTracking) {
                delay(1000) // Update every second
                val currentState = _uiState.value
                if (currentState.isTracking) {
                    val elapsed = System.currentTimeMillis() - currentState.startTime
                    _uiState.value = currentState.copy(elapsedTime = elapsed)
                }
            }
        }
    }
}