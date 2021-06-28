package com.example.wtest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val locationNameNormalized: String= ""
) {
    override fun toString() = "$zipcode-$extZipcode $locationName"
}