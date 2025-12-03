package com.app.idrank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val context = androidx.compose.ui.platform.LocalContext.current
            val androidModule = org.koin.dsl.module {
                single<androidx.room3.RoomDatabase.Builder<com.app.idrank.data.AppDatabase>> {
                    com.app.idrank.data.getDatabaseBuilder(context.applicationContext)
                }
            }
            App(androidModule)
        }
    }
}
