package com.example.wtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wtest.data.database.datasource.ZipcodeDataSource
import com.example.wtest.data.entities.Zipcode

@Database(
    entities = [(Zipcode::class)],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun zipcodeDataSource(): ZipcodeDataSource

    companion object {
        const val DATA_BASE_NAME = "w_test_data_base"
    }

}
