package com.example.wtest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wtest.data.entities.Zipcode
import com.example.wtest.data.webservice.WebService
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivityViewModel(private val ws: WebService) : ViewModel() {

    fun getZipCode() {
        viewModelScope.launch {
            val byteStream = ws.getZipCodes().body()?.byteStream()

            val bufferReader = BufferedReader(InputStreamReader(byteStream)).use(BufferedReader::readText)

            val iteratorSize = bufferReader.lines().size
            var count = 0
            val iterator = bufferReader.lineSequence().iterator()
            val zipCodes = mutableListOf<Zipcode>()

            if(iterator.hasNext()) iterator.next()
            while (iterator.hasNext()) {
                val line = iterator.next()
                val split = line.split(",")
//                zipCodes.add(split.toZipcode())
                Log.d("--", "${(count++)}/$iteratorSize")
            }


            print("--")

        }
    }

    private fun List<String>.toZipcode() : Zipcode {
        return Zipcode(
            this.getOrNull(0).orEmpty(),
            this.getOrNull(1).orEmpty(),
            this.getOrNull(2).orEmpty(),
            this.getOrNull(3).orEmpty(),
            this.getOrNull(4).orEmpty(),
            this.getOrNull(5).orEmpty(),
            this.getOrNull(6).orEmpty(),
            this.getOrNull(7).orEmpty(),
            this.getOrNull(8).orEmpty(),
            this.getOrNull(9).orEmpty(),
            this.getOrNull(10).orEmpty(),
            this.getOrNull(11).orEmpty(),
            this.getOrNull(12).orEmpty(),
            this.getOrNull(13).orEmpty(),
            this.getOrNull(14).orEmpty(),
            this.getOrNull(15).orEmpty(),
            this.getOrNull(16).orEmpty()
        )
    }

}