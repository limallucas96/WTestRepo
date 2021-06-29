package com.example.wtest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val toSearchFull: String= "",
    val toSearchExtZipcode: String= "",
    val toSearchZipcode: String= "",
) {
    override fun toString() = "<b>$zipcode-$extZipcode</b> $locationName"
}

fun List<String>.toZipcode(): Zipcode {
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
        desZipcode = this.getOrNull(16).orEmpty(),
        toSearchFull = "${this.getOrNull(14).orEmpty()} ${this.getOrNull(15).orEmpty()} ${this.getOrNull(3).orEmpty().stripAccents()}",
        toSearchExtZipcode = "${this.getOrNull(15).orEmpty()} ${this.getOrNull(3).orEmpty().stripAccents()}",
        toSearchZipcode = "${this.getOrNull(14).orEmpty()} ${this.getOrNull(3).orEmpty().stripAccents()}"
    )
}