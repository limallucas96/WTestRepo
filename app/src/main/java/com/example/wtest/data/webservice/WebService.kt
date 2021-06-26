package com.example.wtest.data.webservice

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url
import java.io.Serializable

interface WebService {

    @Streaming
    @GET("centraldedados/codigos_postais/master/data/codigos_postais.csv")
    suspend fun getZipCodes(): Response<ResponseBody>

    @GET
    suspend fun getJsonSample(@Url url: String = "https://jsonplaceholder.typicode.com/posts") : List<JsonSample>

    data class JsonSample(val userId: Int, val id: Int, val title: String, val body: String) : Serializable

}