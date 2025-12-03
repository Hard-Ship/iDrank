package com.app.idrank

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { 
    val iosModule = org.koin.dsl.module {
        single<androidx.room3.RoomDatabase.Builder<com.app.idrank.data.AppDatabase>> {
            com.app.idrank.data.getDatabaseBuilder()
        }
    }
    App(iosModule)
}