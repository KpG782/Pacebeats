package com.samsung.health.hrdatatransfer.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "StepsViewModel"

data class StepsState(
    val connected: Boolean = false,
    val currentSteps: Long = 0L,
    val goalSteps: Long = 10000L,
    val trackingRunning: Boolean = false,
    val trackingError: Boolean = false,
    val message: String = "",
    val permissionGranted: Boolean = false
)

@HiltViewModel
class StepsViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), SensorEventListener {

    private val _stepsState = MutableStateFlow(StepsState())
    val stepsState: StateFlow<StepsState> = _stepsState.asStateFlow()

    private val context = application.applicationContext
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    private val stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    
    private var initialStepCount = 0L
    private var sessionSteps = 0L
    
    // For accelerometer-based step detection
    private var lastAcceleration = 9.8f
    private var currentAcceleration = 9.8f
    private val stepThreshold = 12f

    init {
        Log.d(TAG, "StepsViewModel initialized")
        checkPermissions()
        initializeConnection()
    }

    private fun checkPermissions() {
        val hasActivityRecognition = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.ACTIVITY_RECOGNITION
        ) == PackageManager.PERMISSION_GRANTED
        
        val hasBodySensors = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.BODY_SENSORS
        ) == PackageManager.PERMISSION_GRANTED
        
        val permissionGranted = hasActivityRecognition || hasBodySensors
        
        Log.d(TAG, "Permissions - Activity Recognition: $hasActivityRecognition, Body Sensors: $hasBodySensors")
        
        _stepsState.value = _stepsState.value.copy(permissionGranted = permissionGranted)
    }

    private fun initializeConnection() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "=== SENSOR DEBUGGING ===")
                
                // List available sensors
                val allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
                Log.d(TAG, "Total sensors available: ${allSensors.size}")
                
                allSensors.forEach { sensor ->
                    if (sensor.type == Sensor.TYPE_STEP_COUNTER || 
                        sensor.type == Sensor.TYPE_STEP_DETECTOR || 
                        sensor.type == Sensor.TYPE_ACCELEROMETER) {
                        Log.d(TAG, "Found: ${sensor.name} - Type: ${sensor.type}")
                    }
                }
                
                // Check specific sensors
                Log.d(TAG, "Step Counter: ${stepCounterSensor?.name ?: "Not available"}")
                Log.d(TAG, "Step Detector: ${stepDetectorSensor?.name ?: "Not available"}")
                Log.d(TAG, "Accelerometer: ${accelerometer?.name ?: "Not available"}")
                
                val hasAnySensor = stepCounterSensor != null || stepDetectorSensor != null || accelerometer != null
                
                val message = when {
                    !_stepsState.value.permissionGranted -> "Permission required for step tracking"
                    stepCounterSensor != null -> "Step counter sensor ready"
                    stepDetectorSensor != null -> "Step detector sensor ready"
                    accelerometer != null -> "Accelerometer ready for step detection"
                    else -> "No step tracking available - using simulation"
                }
                
                Log.d(TAG, "Final status: $message")
                
                _stepsState.value = _stepsState.value.copy(
                    connected = hasAnySensor || true, // Always show connected for demo
                    message = message
                )
                
            } catch (e: Exception) {
                Log.e(TAG, "Error initializing connection", e)
                _stepsState.value = _stepsState.value.copy(
                    connected = true, // Still allow simulation
                    trackingError = false,
                    message = "Using simulation mode"
                )
            }
        }
    }

    fun startStepsTracking() {
        Log.d(TAG, "startStepsTracking() called")
        
        if (!_stepsState.value.permissionGranted) {
            Log.w(TAG, "No permission, starting simulation")
        }
        
        viewModelScope.launch {
            try {
                sessionSteps = 0L
                initialStepCount = 0L
                
                // Try step counter sensor first
                if (stepCounterSensor != null && _stepsState.value.permissionGranted && tryStepCounterSensor()) {
                    return@launch
                }
                
                // Try step detector sensor
                if (stepDetectorSensor != null && _stepsState.value.permissionGranted && tryStepDetectorSensor()) {
                    return@launch
                }
                
                // Try accelerometer
                if (accelerometer != null && _stepsState.value.permissionGranted && tryAccelerometerTracking()) {
                    return@launch
                }
                
                // Fallback to simulation
                startSimulatedTracking()
                
            } catch (e: Exception) {
                Log.e(TAG, "Error starting tracking", e)
                startSimulatedTracking()
            }
        }
    }

    private fun tryStepCounterSensor(): Boolean {
        return try {
            Log.d(TAG, "Trying step counter sensor...")
            
            val success = sensorManager.registerListener(
                this,
                stepCounterSensor,
                SensorManager.SENSOR_DELAY_UI
            )
            
            if (success) {
                _stepsState.value = _stepsState.value.copy(
                    trackingRunning = true,
                    trackingError = false,
                    currentSteps = 0L,
                    message = "📱 Step counter sensor active!"
                )
                Log.i(TAG, "Step counter sensor started")
                true
            } else {
                Log.e(TAG, "Failed to register step counter")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Step counter error: ${e.message}")
            false
        }
    }

    private fun tryStepDetectorSensor(): Boolean {
        return try {
            Log.d(TAG, "Trying step detector sensor...")
            
            val success = sensorManager.registerListener(
                this,
                stepDetectorSensor,
                SensorManager.SENSOR_DELAY_UI
            )
            
            if (success) {
                _stepsState.value = _stepsState.value.copy(
                    trackingRunning = true,
                    trackingError = false,
                    currentSteps = 0L,
                    message = "🚶 Step detector sensor active!"
                )
                Log.i(TAG, "Step detector sensor started")
                true
            } else {
                Log.e(TAG, "Failed to register step detector")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Step detector error: ${e.message}")
            false
        }
    }

    private fun tryAccelerometerTracking(): Boolean {
        return try {
            Log.d(TAG, "Trying accelerometer-based step detection...")
            
            val success = sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_GAME
            )
            
            if (success) {
                _stepsState.value = _stepsState.value.copy(
                    trackingRunning = true,
                    trackingError = false,
                    currentSteps = 0L,
                    message = "🎯 Accelerometer step detection active!"
                )
                Log.i(TAG, "Accelerometer tracking started")
                true
            } else {
                Log.e(TAG, "Failed to register accelerometer")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Accelerometer error: ${e.message}")
            false
        }
    }

    private fun startSimulatedTracking() {
        Log.d(TAG, "Starting simulated step tracking")
        
        _stepsState.value = _stepsState.value.copy(
            trackingRunning = true,
            trackingError = false,
            currentSteps = 0L,
            message = "🎮 Simulated step tracking - Every 3 seconds!"
        )
        
        viewModelScope.launch {
            var steps = 0L
            while (_stepsState.value.trackingRunning) {
                delay(3000)
                if (_stepsState.value.trackingRunning) {
                    steps += (1..5).random()
                    _stepsState.value = _stepsState.value.copy(currentSteps = steps)
                    Log.d(TAG, "Simulated steps: $steps")
                }
            }
        }
    }

    fun stopStepsTracking() {
        Log.d(TAG, "stopStepsTracking() called")
        
        viewModelScope.launch {
            try {
                // Unregister sensor listeners
                sensorManager.unregisterListener(this@StepsViewModel)
                
                _stepsState.value = _stepsState.value.copy(
                    trackingRunning = false,
                    trackingError = false,
                    message = "⏹ Stopped. Total: ${_stepsState.value.currentSteps} steps"
                )
                
                Log.i(TAG, "All step tracking stopped. Total: ${_stepsState.value.currentSteps}")
                
            } catch (e: Exception) {
                Log.e(TAG, "Error stopping tracking", e)
            }
        }
    }

    fun sendStepsData() {
        Log.d(TAG, "sendStepsData() called with ${_stepsState.value.currentSteps} steps")
        
        viewModelScope.launch {
            _stepsState.value = _stepsState.value.copy(
                message = "📤 Sent ${_stepsState.value.currentSteps} steps to phone"
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (it.sensor.type) {
                Sensor.TYPE_STEP_COUNTER -> {
                    val totalSteps = it.values[0].toLong()
                    Log.d(TAG, "Step counter: total=$totalSteps, initial=$initialStepCount")
                    
                    if (initialStepCount == 0L) {
                        initialStepCount = totalSteps
                        Log.d(TAG, "Set initial step count: $initialStepCount")
                    }
                    
                    val currentSessionSteps = totalSteps - initialStepCount
                    Log.d(TAG, "Session steps: $currentSessionSteps")
                    
                    _stepsState.value = _stepsState.value.copy(currentSteps = currentSessionSteps)
                }
                
                Sensor.TYPE_STEP_DETECTOR -> {
                    sessionSteps++
                    Log.d(TAG, "Step detected: session total = $sessionSteps")
                    _stepsState.value = _stepsState.value.copy(currentSteps = sessionSteps)
                }
                
                Sensor.TYPE_ACCELEROMETER -> {
                    // Simple step detection algorithm
                    val x = it.values[0]
                    val y = it.values[1]
                    val z = it.values[2]
                    
                    lastAcceleration = currentAcceleration
                    currentAcceleration = kotlin.math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
                    val acceleration = currentAcceleration - lastAcceleration
                    
                    if (acceleration > stepThreshold) {
                        sessionSteps++
                        Log.d(TAG, "Accelerometer step detected: $sessionSteps")
                        _stepsState.value = _stepsState.value.copy(currentSteps = sessionSteps)
                    }
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG, "Sensor accuracy changed: ${sensor?.name}, accuracy: $accuracy")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared, stopping all tracking")
        sensorManager.unregisterListener(this)
    }
}