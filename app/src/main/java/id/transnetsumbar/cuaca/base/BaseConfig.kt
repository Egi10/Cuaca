package id.transnetsumbar.cuaca.base

import id.transnetsumbar.cuaca.network.ApiConfig
import id.transnetsumbar.cuaca.network.ApiInterface

open class BaseConfig {
    fun config(): ApiInterface {
        return ApiConfig.config()
    }
}