package com.example.wtest.repository

import android.util.Log
import com.example.wtest.data.database.AppDataBase
import com.example.wtest.data.entities.toZipcode
import com.example.wtest.data.webservice.WebService
import java.io.BufferedReader
import java.io.InputStreamReader

class MainRepository(private val ws: WebService, private val db: AppDataBase) {

    /**
     * TODO:
     *  1. Handle/Treat exceptions
     *  2. Check if has downloaded all content/lines from csv
     *  3. Create mechanism to show download progress
     */

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

    suspend fun getZipcodes(limit: Int, offset: Int) = db.zipcodeDataSource().getZipcodes(limit, offset)

    suspend fun getZipcodesLike(limit: Int, offset: Int, query: String) =
        db.zipcodeDataSource().getZipcodesLike(limit, offset, query)

}
