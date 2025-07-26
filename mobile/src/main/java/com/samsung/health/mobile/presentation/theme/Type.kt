// mobile/src/main/java/com/samsung/health/mobile/presentation/theme/Type.kt
package com.samsung.health.mobile.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

// 1) Use default sans-serif for everything
private val DefaultFamily = FontFamily.SansSerif

// 2) Build a minimal Typography
val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = DefaultFamily,
        fontSize   = 57.sp
    ),
    titleLarge = TextStyle(
        fontFamily = DefaultFamily,
        fontSize   = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = DefaultFamily,
        fontSize   = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = DefaultFamily,
        fontSize   = 14.sp
    )
    // leave other styles at their defaults
)
