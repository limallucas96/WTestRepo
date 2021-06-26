package com.example.wtest.data.entities

//cod_distrito,cod_concelho,cod_localidade,nome_localidade,
// cod_arteria,tipo_arteria,prep1,titulo_arteria,prep2,nome_arteria,
// local_arteria,troco,porta,cliente,num_cod_postal,ext_cod_postal,desig_postal

data class Zipcode(
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
)