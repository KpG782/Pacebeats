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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepCounterView() {
    val context = LocalContext.current
    var isCounting by remember { mutableStateOf(false) }
    var initialStepCount by remember { mutableStateOf(0f) }
    var currentStepCount by remember { mutableStateOf(0f) }
    var startTime by remember { mutableStateOf(0L) }
    var cadence by remember { mutableStateOf(0f) }
    var sensorError by remember { mutableStateOf("") }
    var sensorPresent by remember { mutableStateOf<Boolean?>(null) }
    var sensorReported by remember { mutableStateOf(false) }

    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val stepSensor = remember { sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) }

    DisposableEffect(isCounting) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                sensorReported = true
                if (isCounting) {
                    if (initialStepCount == 0f) {
                        initialStepCount = event.values[0]
                        startTime = System.currentTimeMillis()
                    }
                    currentStepCount = event.values[0] - initialStepCount
                    val elapsedMillis = System.currentTimeMillis() - startTime
                    val elapsedMinutes = elapsedMillis / 60000f
                    cadence = if (elapsedMinutes > 0) currentStepCount / elapsedMinutes else 0f
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        if (isCounting) {
            if (stepSensor == null) {
                sensorPresent = false
            } else {
                sensorPresent = true
                sensorManager.registerListener(listener, stepSensor, SensorManager.SENSOR_DELAY_UI)
            }
        }
        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    val permissionHint = if (android.os.Build.VERSION.SDK_INT >= 29) {
        "Make sure ACTIVITY_RECOGNITION permission is granted in settings."
    } else ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Step Counter",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Card(
            modifier = Modifier
                .size(280.dp)
                .padding(16.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = currentStepCount.toInt().toString(),
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
                    if (isCounting && currentStepCount > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Cadence: ${"%.1f".format(cadence)} spm",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            sensorPresent == false -> {
                Text(
                    text = "Step Counter Sensor not available on this device.",
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
            sensorPresent == true && isCounting && !sensorReported -> {
                Text(
                    text = "Step Counter Sensor present, but not reporting data. Try walking or check permissions.",
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                if (permissionHint.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = permissionHint,
                        color = Color.Yellow,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            sensorError.isNotEmpty() -> {
                Text(
                    text = sensorError,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (!isCounting) {
                    sensorReported = false
                    if (stepSensor == null) {
                        sensorPresent = false
                        sensorError = ""
                        return@Button
                    } else {
                        sensorPresent = true
                        sensorError = ""
                    }
                    initialStepCount = 0f
                    currentStepCount = 0f
                    cadence = 0f
                }
                isCounting = !isCounting
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isCounting) Color.Red else Color.Cyan,
                disabledContainerColor = Color.Gray
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = if (isCounting) "STOP" else "START",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
