package com.example.wtest.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.wtest.data.database.AppDataBase
import com.example.wtest.data.entities.Zipcode
import com.example.wtest.data.webservice.WebService
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class MainRepository(private val ws: WebService, private val db: AppDataBase) {

    suspend fun getZipCode(): Boolean {
        val zipCodes = db.zipcodeDataSource().getZipcodes()
        return if (zipCodes.isNotEmpty()) {
            Log.d("--", "not empty")
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
                    Log.d("--", zipCode.toString())
                    db.zipcodeDataSource().insertZipcode(zipCode)
                }
                Log.d("--", "done")
                true
            } ?: false
        }
    }

    private fun List<String>.toZipcode(): Zipcode {
        return Zipcode(
            districtCode = this.getOrNull(0).orEmpty(),
            countyCode = this.getOrNull(1).orEmpty(),
            locationCode = this.getOrNull(2).orEmpty(),
            locationName = this.getOrNull(3).orEmpty(),
            arteryCode = this.getOrNull(4).orEmpty(),
            arteryType = this.getOrNull(5).orEmpty(),
            prep1 = this.getOrNull(6).orEmpty(),
            arteryTitle = this.getOrNull(7).orEmpty(),
            prep2 = this.getOrNull(8).orEmpty(),
            nameArtery = this.getOrNull(9).orEmpty(),
            arteryLocation = this.getOrNull(10).orEmpty(),
            thing = this.getOrNull(11).orEmpty(),
            door = this.getOrNull(12).orEmpty(),
            client = this.getOrNull(13).orEmpty(),
            zipcode = this.getOrNull(14).orEmpty(),
            extZipcode = this.getOrNull(15).orEmpty(),
            desZipcode = this.getOrNull(16).orEmpty()
        )
    }

}
