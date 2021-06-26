package com.example.wtest.di

import androidx.room.Room
import com.example.wtest.data.webservice.WebServiceClient
import com.example.wtest.ui.viewmodels.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val webServiceModules = module {
    single { WebServiceClient().create() }
}

val dataSourceModules = module {
//    single { FlickerApiDataSource(get()) }
}

val repositoryModules = module {
//    single { FlickerRepository(get(), get()) }
}

val viewModelModules = module {
    factory { MainActivityViewModel(get()) }
//    factory { FullScreenViewModel(get()) }
}

val databaseModules = module {
//    single {
//        Room.databaseBuilder(androidContext(), AppDataBase::class.java, BuildConfig.DATABASE_NAME)
//            .allowMainThreadQueries().fallbackToDestructiveMigration()
//            .build()
//    }
}

val applicationModules = listOf(
    webServiceModules,
    dataSourceModules,
    repositoryModules,
    viewModelModules,
    databaseModules
)