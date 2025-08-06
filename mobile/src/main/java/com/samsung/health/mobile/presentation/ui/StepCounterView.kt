package com.samsung.health.mobile.presentation.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepCounterView() {
    val context = LocalContext.current
    var stepCount by remember { mutableStateOf(0) }
    var isTracking by remember { mutableStateOf(false) }
    var sensorError by remember { mutableStateOf("") }
    var initialStepCount by remember { mutableStateOf(0) }
    var isLoading by remember { mutableStateOf(false) }
    var sensorInitialized by remember { mutableStateOf(false) }
    var useManualMode by remember { mutableStateOf(false) }

    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val stepSensor = remember { sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) }
    val stepDetector = remember { sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) }

    // Calculate pacing (steps per minute) - simplified calculation
    val pacing = if (stepCount > 0) (stepCount * 2).coerceAtMost(180) else 0

    // Timeout for initialization
    LaunchedEffect(isLoading) {
        if (isLoading && !useManualMode) {
            delay(3000) // 3 second timeout
            if (!sensorInitialized) {
                isLoading = false
                sensorError = "Sensor busy or unavailable. Try manual mode or restart the app."
            }
        }
    }

    DisposableEffect(isTracking, useManualMode) {
        if (isTracking && !useManualMode) {
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    when (event.sensor.type) {
                        Sensor.TYPE_STEP_COUNTER -> {
                            val totalSteps = event.values[0].toInt()
                            if (initialStepCount == 0) {
                                initialStepCount = totalSteps
                                sensorInitialized = true
                                isLoading = false
                                sensorError = ""
                            }
                            stepCount = totalSteps - initialStepCount
                        }
                        Sensor.TYPE_STEP_DETECTOR -> {
                            stepCount += 1
                            if (!sensorInitialized) {
                                sensorInitialized = true
                                isLoading = false
                                sensorError = ""
                            }
                        }
                    }
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }

            // Try step counter first, then step detector as fallback
            var registered = false
            isLoading = true
            sensorInitialized = false
            sensorError = ""

            if (stepSensor != null) {
                registered = sensorManager.registerListener(
                    listener, 
                    stepSensor, 
                    SensorManager.SENSOR_DELAY_UI
                )
            }
            
            if (!registered && stepDetector != null) {
                registered = sensorManager.registerListener(
                    listener, 
                    stepDetector, 
                    SensorManager.SENSOR_DELAY_UI
                )
            }
            
            if (!registered) {
                sensorError = "Step sensors are busy or unavailable. Other apps may be using them."
                isLoading = false
            }

            onDispose {
                sensorManager.unregisterListener(listener)
            }
        } else {
            sensorInitialized = false
            onDispose { }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Step Counter",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Mode indicator
        if (useManualMode) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF4A4A4A)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Manual Mode - Tap to count steps",
                    color = Color.Cyan,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        // Main step counter display
        Card(
            modifier = Modifier
                .size(280.dp)
                .padding(16.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
            onClick = if (useManualMode && isTracking) {
                { stepCount += 1 }
            } else {
                { }
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading && !useManualMode) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            color = Color.Cyan,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Connecting to sensor...",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Please wait",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$stepCount",
                            color = Color.Cyan,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "STEPS",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        
                        if (isTracking && stepCount > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "~$pacing steps/min",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }

                        if (useManualMode && isTracking) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "TAP TO COUNT",
                                color = Color.Cyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Status information
        if (sensorError.isNotEmpty() && !useManualMode) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF8B0000)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = sensorError,
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Try closing other fitness apps or use manual mode",
                        color = Color.LightGray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else if (isTracking && stepCount == 0 && !isLoading && (sensorInitialized || useManualMode)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF8B8000)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (useManualMode) {
                        "Tap the circle above to count your steps manually"
                    } else {
                        "Start walking to see your steps!\nSensor is ready and waiting."
                    },
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Control buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Main tracking button
            Button(
                onClick = {
                    if (!isTracking) {
                        stepCount = 0
                        initialStepCount = 0
                        sensorError = ""
                        sensorInitialized = false
                    }
                    isTracking = !isTracking
                },
                modifier = Modifier
                    .weight(2f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isTracking) Color.Red else Color.Cyan,
                    disabledContainerColor = Color.Gray
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = if (isTracking) "STOP" else "START",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Mode toggle button
            Button(
                onClick = {
                    useManualMode = !useManualMode
                    if (isTracking) {
                        isTracking = false
                        stepCount = 0
                        initialStepCount = 0
                        sensorError = ""
                    }
                },
                modifier = Modifier
                    .weight(1.5f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (useManualMode) Color(0xFF8B8000) else Color.Gray
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = if (useManualMode) "AUTO" else "MANUAL",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Reset button
        Button(
            onClick = {
                stepCount = 0
                initialStepCount = 0
                sensorError = ""
                sensorInitialized = false
                isLoading = false
                isTracking = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A4A4A)
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "RESET",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (isTracking && !useManualMode) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Keep your phone with you while walking",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}