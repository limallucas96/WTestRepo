package com.example.wtest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wtest.data.entities.ZipcodeValues.ARTERY_CODE
import com.example.wtest.data.entities.ZipcodeValues.ARTERY_LOCATION
import com.example.wtest.data.entities.ZipcodeValues.ARTERY_NAME
import com.example.wtest.data.entities.ZipcodeValues.ARTERY_TITLE
import com.example.wtest.data.entities.ZipcodeValues.ARTERY_TYPE
import com.example.wtest.data.entities.ZipcodeValues.CLIENT
import com.example.wtest.data.entities.ZipcodeValues.COUNTY_CODE
import com.example.wtest.data.entities.ZipcodeValues.DES_ZIPCODE
import com.example.wtest.data.entities.ZipcodeValues.DISTRICT_CODE
import com.example.wtest.data.entities.ZipcodeValues.DOOR
import com.example.wtest.data.entities.ZipcodeValues.EXT_ZIPCODE
import com.example.wtest.data.entities.ZipcodeValues.LOCATION_CODE
import com.example.wtest.data.entities.ZipcodeValues.LOCATION_NAME
import com.example.wtest.data.entities.ZipcodeValues.PREP_1
import com.example.wtest.data.entities.ZipcodeValues.PREP_2
import com.example.wtest.data.entities.ZipcodeValues.THING
import com.example.wtest.data.entities.ZipcodeValues.ZIPCODE
import com.example.wtest.utils.stripAccents

@Entity(tableName = "zipcode_entity")
data class Zipcode(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val districtCode: String = "",
    val countyCode: String = "",
    val locationCode: String = "",
    val locationName: String = "",
    val arteryCode: String = "",
    val arteryType: String = "",
    val prep1: String = "",
    val arteryTitle: String = "",
    val prep2: String = "",
    val nameArtery: String = "",
    val arteryLocation: String = "",
    val thing: String = "",
    val door: String = "",
    val client: String = "",
    val zipcode: String = "",
    val extZipcode: String = "",
    val desZipcode: String = "",
    //Below parameters are used to perform queries in database.
    val toSearchFull: String = "",
    val toSearchExtZipcode: String = "",
    val toSearchZipcode: String = "",
    val toNameExtZipcode: String = "",
    val toNameSearchZipcode: String = "",
) {
    override fun toString() = "<b>$zipcode-$extZipcode</b> $locationName"
}

object ZipcodeValues {
    //Each index represents each csv column
    const val DISTRICT_CODE = 0
    const val COUNTY_CODE = 1
    const val LOCATION_CODE = 2
    const val LOCATION_NAME = 3
    const val ARTERY_CODE = 4
    const val ARTERY_TYPE = 5
    const val PREP_1 = 6
    const val ARTERY_TITLE = 7
    const val PREP_2 = 8
    const val ARTERY_NAME = 9
    const val ARTERY_LOCATION = 10
    const val THING = 11
    const val DOOR = 12
    const val CLIENT = 13
    const val ZIPCODE = 14
    const val EXT_ZIPCODE = 15
    const val DES_ZIPCODE = 16
}

private fun List<String>.getOrEmpty(index: Int) = getOrNull(index).orEmpty()

//Converts splitted string into ZipCode object
fun List<String>.toZipcode(): Zipcode {
    return Zipcode(
        districtCode = this.getOrEmpty(DISTRICT_CODE),
        countyCode = this.getOrEmpty(COUNTY_CODE),
        locationCode = this.getOrEmpty(LOCATION_CODE),
        locationName = this.getOrEmpty(LOCATION_NAME),
        arteryCode = this.getOrEmpty(ARTERY_CODE),
        arteryType = this.getOrEmpty(ARTERY_TYPE),
        prep1 = this.getOrEmpty(PREP_1),
        arteryTitle = this.getOrEmpty(ARTERY_TITLE),
        prep2 = this.getOrEmpty(PREP_2),
        nameArtery = this.getOrEmpty(ARTERY_NAME),
        arteryLocation = this.getOrEmpty(ARTERY_LOCATION),
        thing = this.getOrEmpty(THING),
        door = this.getOrEmpty(DOOR),
        client = this.getOrEmpty(CLIENT),
        zipcode = this.getOrEmpty(ZIPCODE),
        extZipcode = this.getOrEmpty(EXT_ZIPCODE),
        desZipcode = this.getOrEmpty(DES_ZIPCODE),
        toSearchFull = "${this.getOrEmpty(ZIPCODE)} ${this.getOrEmpty(EXT_ZIPCODE)} ${this.getOrEmpty(LOCATION_NAME).stripAccents()}",
        toSearchExtZipcode = "${this.getOrEmpty(EXT_ZIPCODE)} ${this.getOrEmpty(LOCATION_NAME).stripAccents()}",
        toSearchZipcode = "${this.getOrEmpty(ZIPCODE)} ${this.getOrEmpty(3).stripAccents()}",
        toNameExtZipcode = "${this.getOrEmpty(LOCATION_NAME).stripAccents()} ${this.getOrEmpty(ZIPCODE)}",
        toNameSearchZipcode = "${this.getOrEmpty(LOCATION_NAME).stripAccents()} ${this.getOrEmpty(EXT_ZIPCODE)}"
    )
}