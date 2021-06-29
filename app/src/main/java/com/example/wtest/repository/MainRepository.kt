package com.example.wtest.repository

import android.util.Log
import com.example.wtest.data.database.AppDataBase
import com.example.wtest.data.entities.toZipcode
import com.example.wtest.data.webservice.WebService
import java.io.BufferedReader
import java.io.InputStreamReader

class MainRepository(private val ws: WebService, private val db: AppDataBase) {

    suspend fun getZipCode(): Boolean {
        val zipCodes = db.zipcodeDataSource().checkDatabase()
        return if (zipCodes.isNotEmpty()) {
            true
        } else {
            ws.getZipCodes().body()?.byteStream()?.let { byteStream ->

                val bufferReader = BufferedReader(InputStreamReader(byteStream)).use(BufferedReader::readText)

                val iterator = bufferReader.lineSequence().iterator()

                if (iterator.hasNext()) iterator.next() //Ignores first line.

                while (iterator.hasNext()) {
                    val line = iterator.next()
                    val split = line.split(",")
                    val zipCode = split.toZipcode()
                    db.zipcodeDataSource().insertZipcode(zipCode)
                    Log.d("--", zipCode.toString())
                }
                true
            } ?: false
        }
    }

    suspend fun getZipcodes() = db.zipcodeDataSource().getZipcodes()

    suspend fun getZipcodesLike(query: String) = db.zipcodeDataSource().getZipcodesLike(query)

}
