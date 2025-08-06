/*
 * Copyright 2023 Samsung Electronics Co., Ltd. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samsung.health.hrdatatransfer.presentation.ui

import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.samsung.health.hrdatatransfer.R
import com.samsung.health.hrdatatransfer.presentation.MainViewModel
import com.samsung.health.hrdatatransfer.presentation.theme.HRDataTransferTheme

private const val TAG = "MainScreen"

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    connected: Boolean,
    connectionMessage: String,
    trackingRunning: Boolean,
    trackingError: Boolean,
    trackingMessage: String,
    valueHR: String,
    valueIBI: ArrayList<Int>,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onSend: () -> Unit,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Log.i(TAG, "MainScreen Composable")
    HRDataTransferTheme {
        val pagerState = rememberPagerState(pageCount = { 3 }) // 3 pages

        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> MainPageContent(
                    connected = connected,
                    connectionMessage = connectionMessage,
                    trackingRunning = trackingRunning,
                    trackingError = trackingError,
                    trackingMessage = trackingMessage,
                    valueHR = valueHR,
                    valueIBI = valueIBI,
                    onStart = onStart,
                    onStop = onStop,
                    onSend = onSend
                )
                1 -> StepsPage(viewModel)
                2 -> ImpressivePage()
            }
        }
    }
}

// Extracted original main UI for clarity
@Composable
fun MainPageContent(
    connected: Boolean,
    connectionMessage: String,
    trackingRunning: Boolean,
    trackingError: Boolean,
    trackingMessage: String,
    valueHR: String,
    valueIBI: ArrayList<Int>,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onSend: () -> Unit
) {
    ShowConnectionMessage(connected = connected, connectionMessage = connectionMessage)
    if (trackingMessage != "") ShowToast(trackingMessage)

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.width(90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = Color.LightGray,
                text = "HR",
                fontSize = 11.sp,
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                color = Color.White,
                text = valueHR,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.size(11.dp))
            Box(
                modifier = Modifier.size(54.dp)
            ) {
                var startButtonPressed by remember { mutableStateOf(false) }
                Button(
                    onClick = {
                        startButtonPressed = if (trackingRunning) {
                            onStop.invoke()
                            false
                        } else {
                            onStart.invoke()
                            true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (trackingRunning) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary
                    ),
                    enabled = connected,
                    modifier = Modifier
                        .size(54.dp),
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp,
                        color = MaterialTheme.colors.onSecondary,
                        text = if (trackingRunning) stringResource(R.string.stop_button)
                        else stringResource(
                            R.string.start_button
                        ),
                    )
                }
                if (!trackingError && !trackingRunning && startButtonPressed) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        indicatorColor = Color.Black,
                        strokeWidth = 4.dp
                    )
                }
            }
        }
        Spacer(Modifier.size(2.dp))
        Column(
            modifier = Modifier.width(90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = Color.LightGray,
                text = "IBI",
                fontSize = 11.sp,
                textAlign = TextAlign.Right
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                color = Color.White,
                text = valueIBI.getOrElse(0) { "-" }.toString(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                color = Color.White,
                text = valueIBI.getOrElse(1) { "-" }.toString(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                color = Color.White,
                text = valueIBI.getOrElse(2) { "-" }.toString(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                color = Color.White,
                text = valueIBI.getOrElse(3) { "-" }.toString(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.size(11.dp))
            Button(
                onClick = onSend,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                enabled = connected,
                modifier = Modifier
                    .size(52.dp)
            ) {
                Text(
                    fontSize = 11.sp,
                    color = MaterialTheme.colors.onSecondary,
                    text = "SEND",
                )
            }
        }
    }
}

@Composable
fun ShowConnectionMessage(
    connected: Boolean,
    connectionMessage: String
) {
    Log.i(TAG, "connectionMessage: $connectionMessage, connected: $connected")
    if (connectionMessage != "" && connected) {
        ShowToast(connectionMessage)
    }
}
@Composable
fun ShowToast(message: String) {
    makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}