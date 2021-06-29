package com.example.wtest.di

import androidx.room.Room
import com.example.wtest.data.database.AppDataBase
import com.example.wtest.data.database.AppDataBase.Companion.DATA_BASE_NAME
import com.example.wtest.data.webservice.WebServiceClient
import com.example.wtest.repository.MainRepository
import com.example.wtest.ui.viewmodels.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val webServiceModules = module {
    single { WebServiceClient().create() }
}

val repositoryModules = module {
    single { MainRepository(get(), get()) }
}

val viewModelModules = module {
    factory { MainActivityViewModel(get()) }
}

val databaseModules = module {
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, DATA_BASE_NAME)
            .allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()
    }
}

val applicationModules = listOf(
    webServiceModules,
    repositoryModules,
    viewModelModules,
    databaseModules
)