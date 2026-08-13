package org.dergigi.offlineentropymanual.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Seed = Color(0xFF2D5A3D)
private val OnSeed = Color(0xFFE8E4D9)
private val SurfaceLight = Color(0xFFF5F2EA)
private val SurfaceDark = Color(0xFF141414)
private val OnSurfaceLight = Color(0xFF1A1A1A)
private val OnSurfaceDark = Color(0xFFE8E4D9)

private val LightColors = lightColorScheme(
    primary = Seed,
    onPrimary = OnSeed,
    secondary = Color(0xFF6B9E78),
    onSecondary = Color(0xFF0E1A12),
    background = SurfaceLight,
    onBackground = OnSurfaceLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF6B9E78),
    onPrimary = Color(0xFF0E1A12),
    secondary = Color(0xFF8FBE9A),
    onSecondary = Color(0xFF0E1A12),
    background = SurfaceDark,
    onBackground = OnSurfaceDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
)

@Composable
fun OfflineEntropyManualTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
