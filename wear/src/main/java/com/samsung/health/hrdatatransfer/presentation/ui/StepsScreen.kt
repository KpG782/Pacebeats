package com.samsung.health.hrdatatransfer.presentation.ui

import android.Manifest
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.samsung.health.hrdatatransfer.presentation.viewmodel.StepsViewModel

private const val TAG = "StepsScreen"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StepsScreen() {
    val viewModel: StepsViewModel = hiltViewModel()
    val stepsState by viewModel.stepsState.collectAsStateWithLifecycle()
    
    // Request multiple permissions
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.BODY_SENSORS
        )
    )
    
    // Request permissions on first launch
    LaunchedEffect(Unit) {
        if (!permissionsState.allPermissionsGranted) {
            permissionsState.launchMultiplePermissionRequest()
        }
    }
    
    Log.i(TAG, "StepsScreen - Live Steps: ${stepsState.currentSteps}, Tracking: ${stepsState.trackingRunning}")
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1A2E),
                        Color(0xFF16213E),
                        Color(0xFF0F3460)
                    )
                )
            )
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        // Header
        item {
            Text(
                text = "Live Step Tracker",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        // Permission Status
        if (!permissionsState.allPermissionsGranted) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    backgroundPainter = CardDefaults.cardBackgroundPainter(
                        startBackgroundColor = Color(0xFFFF9800).copy(alpha = 0.15f),
                        endBackgroundColor = Color.White.copy(alpha = 0.05f)
                    ),
                    onClick = { permissionsState.launchMultiplePermissionRequest() }
                ) {
                    Text(
                        text = "⚠ Tap to grant step tracking permissions",
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
        
        // Connection Status
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                backgroundPainter = CardDefaults.cardBackgroundPainter(
                    startBackgroundColor = if (stepsState.connected && permissionsState.allPermissionsGranted) 
                        Color(0xFF4CAF50).copy(alpha = 0.15f) 
                    else 
                        Color(0xFFFF5722).copy(alpha = 0.15f),
                    endBackgroundColor = Color.White.copy(alpha = 0.05f)
                ),
                onClick = { }
            ) {
                Text(
                    text = if (stepsState.connected && permissionsState.allPermissionsGranted) 
                        "✓ Health Services Ready" 
                    else 
                        "⚠ Setting up step tracking...",
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
        
        // Main Steps Display - LIVE DATA
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(20.dp),
                backgroundPainter = CardDefaults.cardBackgroundPainter(
                    startBackgroundColor = if (stepsState.trackingRunning)
                        Color(0xFF4CAF50).copy(alpha = 0.2f)
                    else
                        Color.White.copy(alpha = 0.1f),
                    endBackgroundColor = Color.White.copy(alpha = 0.05f)
                ),
                onClick = { }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Timeline,
                        contentDescription = "Steps",
                        modifier = Modifier.size(40.dp),
                        tint = if (stepsState.trackingRunning) Color(0xFF4CAF50) else Color.White
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        text = stepsState.currentSteps.toString(),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    
                    Text(
                        text = if (stepsState.trackingRunning) "steps (live)" else "steps",
                        fontSize = 14.sp,
                        color = if (stepsState.trackingRunning) 
                            Color(0xFF4CAF50) 
                        else 
                            Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }
        
        // Progress Circle
        item {
            val progress = (stepsState.currentSteps.toFloat() / stepsState.goalSteps.toFloat()).coerceIn(0f, 1f)
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = progress,
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 8.dp,
                    trackColor = Color.White.copy(alpha = 0.1f),
                    indicatorColor = Color(0xFF4CAF50)
                )
                
                Text(
                    text = "${(progress * 100).toInt()}%",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        // Control Buttons - START/STOP REAL TRACKING
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Start/Stop Button
                Button(
                    onClick = {
                        Log.d(TAG, "Button clicked - Current state: tracking=${stepsState.trackingRunning}, connected=${stepsState.connected}, permissions=${permissionsState.allPermissionsGranted}")
                        
                        if (stepsState.trackingRunning) {
                            viewModel.stopStepsTracking()
                            Log.i(TAG, "Step tracking STOPPED")
                        } else {
                            viewModel.startStepsTracking()
                            Log.i(TAG, "Step tracking STARTED")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (stepsState.trackingRunning) 
                            Color(0xFFFF5722) // Red for stop
                        else 
                            Color(0xFF4CAF50) // Green for start
                    ),
                    enabled = stepsState.connected && permissionsState.allPermissionsGranted,
                    modifier = Modifier.size(64.dp),
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.White,
                        text = if (stepsState.trackingRunning) "STOP" else "START",
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Send Button
                Button(
                    onClick = {
                        viewModel.sendStepsData()
                        Log.i(TAG, "Sending ${stepsState.currentSteps} steps to phone")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF2196F3)
                    ),
                    enabled = stepsState.connected && stepsState.currentSteps > 0 && permissionsState.allPermissionsGranted,
                    modifier = Modifier.size(52.dp)
                ) {
                    Text(
                        fontSize = 11.sp,
                        color = Color.White,
                        text = "SEND",
                    )
                }
            }
        }
        
        // Status Message
        if (stepsState.message.isNotEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    backgroundPainter = CardDefaults.cardBackgroundPainter(
                        startBackgroundColor = if (stepsState.trackingError) 
                            Color(0xFFFF5722).copy(alpha = 0.15f)
                        else 
                            Color(0xFF2196F3).copy(alpha = 0.15f),
                        endBackgroundColor = Color.White.copy(alpha = 0.05f)
                    ),
                    onClick = { }
                ) {
                    Text(
                        text = stepsState.message,
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}