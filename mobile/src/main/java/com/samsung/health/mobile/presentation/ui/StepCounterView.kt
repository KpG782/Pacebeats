package com.samsung.health.mobile.presentation.ui


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepCounterView() {
    val context = LocalContext.current
    var isCounting by remember { mutableStateOf(false) }
    var initialStepCount by remember { mutableStateOf(0f) }
    var currentStepCount by remember { mutableStateOf(0f) }
    var startTime by remember { mutableStateOf(0L) }
    var pacing by remember { mutableStateOf(0f) }
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
                    val elapsedMinutes = (System.currentTimeMillis() - startTime) / 60000f
                    pacing = if (elapsedMinutes > 0) currentStepCount / elapsedMinutes else 0f
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

    // Permission check (not runtime, just a hint for user)
    val permissionHint = if (android.os.Build.VERSION.SDK_INT >= 29) {
        "Make sure ACTIVITY_RECOGNITION permission is granted in settings."
    } else ""

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Step Counter",
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(16.dp))
        when {
            sensorPresent == false -> {
                Text(text = "Step Counter Sensor not available on this device.", color = Color.Red)
            }
            sensorPresent == true && isCounting && !sensorReported -> {
                Text(text = "Step Counter Sensor present, but not reporting data. Try walking or check permissions.", color = Color.Red)
                if (permissionHint.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = permissionHint, color = Color.Yellow, fontSize = 14.sp)
                }
            }
            sensorError.isNotEmpty() -> {
                Text(text = sensorError, color = Color.Red)
            }
        }
        if (sensorPresent != false) {
            Text(
                text = "Steps: ${currentStepCount.toInt()}",
                fontSize = 32.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Pacing: ${"%.2f".format(pacing)} steps/min",
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
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
                pacing = 0f
            }
            isCounting = !isCounting
        }) {
            Text(if (isCounting) "Stop" else "Start")
        }
    }
}
