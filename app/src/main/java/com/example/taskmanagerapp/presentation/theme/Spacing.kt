package com.example.taskmanagerapp.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppSpacing(
    val nano: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 20.dp,
    val extraLarge: Dp = 32.dp
)

val LocalSpacing = staticCompositionLocalOf { AppSpacing() }
