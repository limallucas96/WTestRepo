package com.example.wtest.data.webservice

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming

interface WebService {

    @Streaming
    @GET("centraldedados/codigos_postais/master/data/codigos_postais.csv")
    suspend fun getZipCodes(): Response<ResponseBody>

}