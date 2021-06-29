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

    @Query("SELECT * FROM zipcode_entity ORDER BY id LIMIT 1")
    suspend fun checkDatabase(): List<Zipcode>

    @Query("SELECT * FROM zipcode_entity")
    suspend fun getZipcodes(): List<Zipcode>

    @Query("SELECT * FROM zipcode_entity WHERE toSearchFull LIKE '%' || :query || '%' OR toSearchExtZipcode LIKE '%' || :query || '%' OR toSearchZipcode LIKE '%' || :query || '%' OR toNameExtZipcode LIKE '%' || :query || '%' OR toNameSearchZipcode LIKE '%' || :query || '%' OR toNameSearchZipcode LIKE '%' || :query || '%'")
    suspend fun getZipcodesLike(query: String): List<Zipcode>

}