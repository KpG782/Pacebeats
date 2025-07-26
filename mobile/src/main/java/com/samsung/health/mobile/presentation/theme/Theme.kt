package com.samsung.health.mobile.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

// ─── 1) Light & Dark ColorSchemes ─────────────────────────────────────────────
private val LightColors = lightColorScheme(
  primary             = Primary60,
  onPrimary           = Color.White,
  primaryContainer    = Primary20,

  secondary           = AccentModerate,
  onSecondary         = Color.White,
  secondaryContainer  = Primary30,

  background          = Grey10,
  onBackground        = Grey100,

  surface             = Grey20,
  onSurface           = Grey100,

  error               = Color(0xFFB00020),
  onError             = Color.White
)

private val DarkColors = darkColorScheme(
  primary             = Primary80,
  onPrimary           = Color.White,
  primaryContainer    = Primary90,

  secondary           = AccentBold,
  onSecondary         = Color.White,
  secondaryContainer  = Primary70,

  background          = Grey100,
  onBackground        = Grey10,

  surface             = Grey90,
  onSurface           = Grey10,

  error               = Color(0xFFCF6679),
  onError             = Color.Black
)

// ─── 2) Shapes ─────────────────────────────────────────────────────────────────
private val AppShapes = Shapes(
  small  = RoundedCornerShape(4.dp),
  medium = RoundedCornerShape(8.dp),
  large  = RoundedCornerShape(12.dp)
)

// ─── 3) Theme Entry Point ──────────────────────────────────────────────────────
@Composable
fun PaceBeatsTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = if (darkTheme) DarkColors else LightColors,
    typography  = AppTypography,
    shapes      = AppShapes,
    content     = content
  )
}
