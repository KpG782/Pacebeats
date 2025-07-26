package com.samsung.health.hrdatatransfer.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CardDefaults
import androidx.compose.foundation.layout.PaddingValues
import androidx.wear.compose.material.Text

// ---------- Data Models ----------
data class RunningData(
    val title: String,
    val value: String,
    val unit: String,
    val emoji: String,
    val color: Color
)

data class WorkoutSession(
    val type: String,
    val duration: String,
    val distance: String,
    val calories: String,
    val date: String
)

// ---------- Screen ----------
@Composable
fun ImpressivePage() {
    val context = LocalContext.current

    val runningMetrics = listOf(
        RunningData("Distance", "5.2", "km", "🏃", Color(0xFF4CAF50)),
        RunningData("Duration", "28", "min", "⏱️", Color(0xFF2196F3)),
        RunningData("Pace", "5:23", "min/km", "⚡", Color(0xFF9C27B0)),
        RunningData("Calories", "312", "kcal", "🔥", Color(0xFFFF5722)),
        RunningData("Heart Rate", "142", "bpm", "❤️", Color(0xFFE91E63)),
        RunningData("Steps", "7,234", "steps", "👟", Color(0xFF607D8B))
    )

    val workoutSessions = listOf(
        WorkoutSession("Morning Run", "32 min", "6.1 km", "380 cal", "Today"),
        WorkoutSession("Evening Jog", "25 min", "4.8 km", "295 cal", "Yesterday"),
        WorkoutSession("Hill Training", "45 min", "8.2 km", "520 cal", "2 days ago"),
        WorkoutSession("Recovery Run", "20 min", "3.5 km", "210 cal", "3 days ago")
    )

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
        // Header Profile Card
        item {
            Card(
                onClick = {
                    Toast.makeText(context, "Profile tapped!", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(20.dp),
                backgroundPainter = CardDefaults.cardBackgroundPainter(
                    startBackgroundColor = Color.White.copy(alpha = 0.1f),
                    endBackgroundColor = Color.White.copy(alpha = 0.05f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(12.dp, RoundedCornerShape(20.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(Color(0xFF4CAF50), Color(0xFF2E7D32))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "KP",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Ken Patrick Garcia",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Today's Runner 🏃‍♂️",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        // Today's Metrics Section
        item {
            Text(
                text = "Today's Performance",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Metrics Grid
        items(runningMetrics.chunked(2)) { rowMetrics ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowMetrics.forEach { metric ->
                    MetricCard(
                        data = metric,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            Toast.makeText(
                                context,
                                "${metric.title}: ${metric.value} ${metric.unit}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }

        // Recent Workouts Section
        item {
            Text(
                text = "Recent Workouts",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Workout Sessions
        items(workoutSessions) { workout ->
            WorkoutCard(
                session = workout,
                onClick = {
                    Toast.makeText(
                        context,
                        "Viewing ${workout.type} details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }

        // Action Buttons
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Solid button
                Button(
                    onClick = {
                        Toast.makeText(context, "Starting new workout!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF4CAF50)
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("▶️", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Start Workout",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                // Outlined-style button using ButtonDefaults.outlinedButtonBorder
                Button(
                    onClick = {
                        Toast.makeText(context, "Opening analytics!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder(
                        borderColor = Color.White.copy(alpha = 0.5f),
                        borderWidth = 1.dp
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("📊", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "View Analytics",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

// ---------- Reusable Cards ----------
@Composable
fun MetricCard(
    data: RunningData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        backgroundPainter = CardDefaults.cardBackgroundPainter(
            startBackgroundColor = data.color.copy(alpha = 0.15f),
            endBackgroundColor = data.color.copy(alpha = 0.08f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = data.emoji, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.value,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = data.unit,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 10.sp
            )
            Text(
                text = data.title,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun WorkoutCard(
    session: WorkoutSession,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        backgroundPainter = CardDefaults.cardBackgroundPainter(
            startBackgroundColor = Color.White.copy(alpha = 0.08f),
            endBackgroundColor = Color.White.copy(alpha = 0.04f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4CAF50).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text("🏃", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = session.type,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${session.duration} • ${session.distance} • ${session.calories}",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }
            Text(
                text = session.date,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 10.sp
            )
        }
    }
}
