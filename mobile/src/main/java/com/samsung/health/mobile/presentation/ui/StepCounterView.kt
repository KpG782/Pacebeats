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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samsung.health.mobile.presentation.viewmodel.StepCounterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepCounterView(
    viewModel: StepCounterViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val stepSensor = remember { sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) }

    // Sensor listener setup
    DisposableEffect(uiState.isTracking) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                viewModel.onSensorDataReceived(event.values[0])
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        
        if (uiState.isTracking) {
            if (stepSensor == null) {
                viewModel.onSensorError("Step sensor not available")
            } else {
                sensorManager.registerListener(listener, stepSensor, SensorManager.SENSOR_DELAY_UI)
                viewModel.onSensorConnected()
            }
        }
        
        onDispose {
            sensorManager.unregisterListener(listener)
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
        Text(
            text = "Live Step Counter",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Main step display card
        Card(
            modifier = Modifier
                .size(280.dp)
                .padding(16.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = if (uiState.isTracking) Color.DarkGray else Color.Gray
            ),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = uiState.currentSteps.toString(),
                        color = if (uiState.isTracking) Color.Cyan else Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "LIVE STEPS",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    if (uiState.isTracking && uiState.currentSteps > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Pace: ${"%.1f".format(uiState.cadence)} spm",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Duration: ${formatDuration(uiState.elapsedTime)}",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Status and error messages
        when {
            uiState.errorMessage.isNotEmpty() -> {
                Text(
                    text = uiState.errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
            uiState.isTracking && !uiState.sensorReporting -> {
                Text(
                    text = "Sensor connected but no data. Try walking to see live updates!",
                    color = Color.Yellow,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
            uiState.isTracking -> {
                Text(
                    text = "🟢 Live tracking active - Start walking!",
                    color = Color.Green,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Start/Stop button
        Button(
            onClick = {
                if (uiState.isTracking) {
                    viewModel.stopTracking()
                } else {
                    viewModel.startTracking()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (uiState.isTracking) Color.Red else Color.Cyan
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = if (uiState.isTracking) "STOP TRACKING" else "START LIVE TRACKING",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (uiState.totalSessionSteps > 0) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Session Total: ${uiState.totalSessionSteps} steps",
                color = Color.Cyan,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private fun formatDuration(milliseconds: Long): String {
    val seconds = milliseconds / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    return when {
        hours > 0 -> "${hours}h ${minutes % 60}m"
        minutes > 0 -> "${minutes}m ${seconds % 60}s"
        else -> "${seconds}s"
    }
}