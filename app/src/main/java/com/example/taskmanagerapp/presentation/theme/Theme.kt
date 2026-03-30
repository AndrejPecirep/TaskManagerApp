package com.example.taskmanagerapp.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColors = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = AppSurface,
    primaryContainer = SurfaceVariantLight,
    onPrimaryContainer = TextPrimary,
    secondary = BrandSecondary,
    background = AppBackground,
    onBackground = TextPrimary,
    surface = AppSurface,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    error = ErrorRed
)

val MaterialTheme.spacing: AppSpacing
    @Composable
    get() = LocalSpacing.current

@Composable
fun TaskMasterTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSpacing provides AppSpacing()) {
        MaterialTheme(
            colorScheme = LightColors,
            typography = AppTypography,
            content = content
        )
    }
}
