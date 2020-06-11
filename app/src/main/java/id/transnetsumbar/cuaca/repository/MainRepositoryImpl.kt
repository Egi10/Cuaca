package id.transnetsumbar.cuaca.repository

import id.transnetsumbar.cuaca.network.ApiInterface
import id.transnetsumbar.cuaca.network.model.ApiResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Julsapargi Nursam on 6/11/20.
 */

class MainRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface): MainRepository {
    override suspend fun getWeather(id: String?, appId: String?, units: String?): Response<ApiResponse> {
        return apiInterface.getCuaca(id, appId, units)
    }
}