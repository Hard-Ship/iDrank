package com.app.idrank.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val   LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    onPrimary = lightOnPrimary,

    background = lightColorBackground,
    onBackground = lightContentColor,

    surface = lightColorSurface,
    surfaceContainerLow = lightColorSurface,
    surfaceContainerHighest = lightColorSurface,
    surfaceContainerHigh = lightColorSurface,
    surfaceContainerLowest = lightColorSurface,

    onSurface = lightContentColor,



)

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    onPrimary = darkOnPrimary,

    background = darkColorBackground,
    onBackground = darkContentColor,

    surface = darkColorSurface,
    surfaceContainerLow = darkColorSurface,
    surfaceContainerHighest = darkColorSurface,
    surfaceContainerHigh = darkColorSurface,
    surfaceContainerLowest = darkColorSurface,

    onSurface = darkContentColor,

)


@Composable
fun IDrankTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )

}