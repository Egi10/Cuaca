package id.transnetsumbar.cuaca.repository

import id.transnetsumbar.cuaca.network.model.ApiResponse
import retrofit2.Response

/**
 * Created by Julsapargi Nursam on 6/11/20.
 */

interface MainRepository {
    suspend fun getWeather(id: String?, appId: String?, units: String?): Response<ApiResponse>
}