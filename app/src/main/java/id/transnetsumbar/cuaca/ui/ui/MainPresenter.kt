package id.transnetsumbar.cuaca.ui.ui

import id.transnetsumbar.cuaca.base.BaseConfig
import id.transnetsumbar.cuaca.untils.formatTanggal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainPresenter(private val view: MainView) : BaseConfig() {
    fun getCuaca() {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val config = config().getCuaca("1642907", "03207a5afa25a1f6db2d2fcc6dd63fc1", "metric")
                val response = config.await()

                when(response.code()) {
                    200 -> {
                        val list = response.body()?.list
                        view.onSuccess(list, response.body()?.city)

                        val date = Date()
                        val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
                        val dateNow: String = formatter.format(date)

                        val listHour = list?.filter {
                            it.dtTxt?.formatTanggal() == dateNow.formatTanggal()
                        }
                        view.onSuccessHour(listHour)
                    }

                    else -> {
                        view.onError(response.message())
                    }
                }
                view.hideLoading()
            } catch (e: Exception) {
                view.onError(e.message)
                view.hideLoading()
            }
        }
    }
}