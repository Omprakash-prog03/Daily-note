package com.studentos.ui.theme

import android.os.Build
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Design tokens extracted from image-1.png reference
val PrimaryColor = Color(0xFF3B82F6) // Deep Indigo/Blue
val SurfaceColor = Color(0xFFF8FAFC) // Light Gray/White
val AccentSuccess = Color(0xFF10B981) // Green for Safe attendance
val AccentWarning = Color(0xFFF59E0B) // Amber for Warning
val AccentCritical = Color(0xFFEF4444) // Red for Critical
val BackgroundColor = Color(0xFFF5F5F7) // Apple-style neutral

val StudentOSTypography = Typography(
    bodyLarge = androidx.compose.material3.Typography.defaultTypography.bodyLarge.copy(
        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
        fontSize = 16.sp
    ),
    displaySmall = androidx.compose.material3.Typography.defaultTypography.displaySmall.copy(
        fontSize = 28.sp
    ),
    titleMedium = androidx.compose.material3.Typography.defaultTypography.titleMedium.copy(
        fontSize = 20.sp
    )
)

@Composable
fun StudentOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (darkTheme) {
        true -> darkColorScheme(
            primary = PrimaryColor,
            surface = SurfaceColor.copy(alpha = 0.1f),
            background = BackgroundColor.copy(alpha = 0.05f)
        )
        false -> lightColorScheme(
            primary = PrimaryColor,
            surface = SurfaceColor,
            background = BackgroundColor
        )
    }
    val view = LocalView.current
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowInsetsController = view.windowInsetsController
        windowInsetsController?.isAppearanceLightStatusBars = !darkTheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = StudentOSTypography,
        content = content
    )
}

@Composable
fun PaddingValues.topWindowInsets(): androidx.compose.foundation.layout.PaddingValues {
    return androidx.compose.foundation.layout.PaddingValues(top = windowInsetsTopHeight(WindowInsets.systemBars))
}
