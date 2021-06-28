package com.example.wtest.data.database.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wtest.data.entities.Zipcode

@Dao
interface ZipcodeDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZipcode(zipCode: Zipcode)

    @Query("SELECT * FROM 'zipcode_entity'")
    suspend fun getZipcodes(): List<Zipcode>

    @Query("SELECT * FROM zipcode_entity" +
            " WHERE locationNameNormalized LIKE '%' || :query || '%'" +
            " OR zipcode LIKE '%' || :query || '%'" +
            " OR extZipcode LIKE '%' || :query || '%'")
    suspend fun getZipcodesLike(query: String): List<Zipcode>

}