package id.transnetsumbar.cuaca.ui.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.transnetsumbar.cuaca.network.model.ListItem
import id.transnetsumbar.cuaca.repository.MainRepository
import id.transnetsumbar.cuaca.untils.formatTanggal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Julsapargi Nursam on 6/11/20.
 */

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _item = MutableLiveData<List<ListItem>>()
    val item: LiveData<List<ListItem>> get() = _item

    private val _itemHour = MutableLiveData<List<ListItem>>()
    val itemHour: LiveData<List<ListItem>> get() = _itemHour

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _thePlace = MutableLiveData<String>()
    val thePlace: LiveData<String> get() = _thePlace

    init {
        getWeather("1642907", "03207a5afa25a1f6db2d2fcc6dd63fc1", "metric")
    }

    private fun getWeather(id: String?, appId: String?, units: String?) {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val config = repository.getWeather(id, appId, units)

                when(config.code()) {
                    200 -> {
                        val list = config.body()?.list
                        _item.postValue(list)

                        _thePlace.postValue(config.body()?.city?.name)

                        val date = Date()
                        val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
                        val dateNow: String = formatter.format(date)

                        val listHour = list?.filter {
                            it.dtTxt?.formatTanggal() == dateNow.formatTanggal()
                        }
                        _itemHour.postValue(listHour)
                    }

                    else -> {
                        _error.postValue(config.message())
                    }
                }
                _state.value = false
            } catch (e: Exception) {
                _error.postValue(e.message)
                _state.value = false
            }
        }
    }
}