package com.app.idrank

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.app.idrank.di.homeModule
import com.app.idrank.presentation.HomeScreen
import com.app.idrank.presentation.IDrankTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
@Preview
fun App(platformModule: org.koin.core.module.Module? = null) {
    KoinApplication(
        configuration = koinConfiguration {
            if (platformModule != null) {
                modules(platformModule)
            }
            modules(homeModule)
        }
    ) {
        IDrankTheme {
            HomeScreen()
        }
    }
}