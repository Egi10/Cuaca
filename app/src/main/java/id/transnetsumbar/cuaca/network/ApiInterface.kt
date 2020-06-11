package id.transnetsumbar.cuaca.network

import id.transnetsumbar.cuaca.network.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("data/2.5/forecast")
    suspend fun getCuaca(
        @Query("id") id: String?,
        @Query("appid") appid: String?,
        @Query("units") units: String?
    ): Response<ApiResponse>
}