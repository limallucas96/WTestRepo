package com.example.wtest.repository

import com.example.wtest.AppApplication.Companion.doLog
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
     *  3. Create mechanism to show download progress. Like 1/100%.
     *  4. Do unit tests
     *  5. Check android docs on how to test paging3 data source
     */

    suspend fun getZipCode(): Boolean {
        val zipCodes = db.zipcodeDataSource().checkDatabase()
        return if (zipCodes.isNotEmpty()) {
            doLog("Database not empty")
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
                    doLog(zipCode.toString())
                }

                doLog("Fetched all lines from csv")
                true
            } ?: run {
                doLog("Something went wrong")
                false
            }
        }
    }

    suspend fun getZipcodes(limit: Int, offset: Int) = db.zipcodeDataSource().getZipcodes(limit, offset)

    suspend fun getZipcodesLike(limit: Int, offset: Int, query: String) = db.zipcodeDataSource().getZipcodesLike(limit, offset, query)

}
