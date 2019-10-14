package id.transnetsumbar.cuaca.ui.ui

import id.transnetsumbar.cuaca.base.BaseView
import id.transnetsumbar.cuaca.network.model.City
import id.transnetsumbar.cuaca.network.model.ListItem

interface MainView : BaseView {
    fun onSuccess(list: List<ListItem>?, city: City?)
    fun onSuccessHour(list: List<ListItem>?)
    fun onError(error: String?)
}