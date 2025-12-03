package com.app.idrank.di

import com.app.idrank.data.AppDatabase
import com.app.idrank.data.WaterDao
import com.app.idrank.data.WaterRepo
import com.app.idrank.data.getRoomDatabase
import com.app.idrank.domain.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val homeModule = module {

    single<AppDatabase> {
        val builder = get<androidx.room3.RoomDatabase.Builder<AppDatabase>>()
        getRoomDatabase(builder)
    }

    single<WaterDao> {
        get<AppDatabase>().waterDao()
    }
    singleOf(::WaterRepo)
    viewModelOf(::HomeViewModel)

}