package id.transnetsumbar.cuaca.untils

import java.text.SimpleDateFormat
import java.util.*

fun String.formatTanggal(): String? {
    val locale = Locale("id")
    val inputDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", locale)
    val outputDate = SimpleDateFormat("EEEE, dd MMMM yyyy", locale)
    val date = inputDate.parse(this)
    return outputDate.format(date)
}

fun String.formatTanggalToJam(): String? {
    val locale = Locale("id")
    val inputDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", locale)
    val outputDate = SimpleDateFormat("hh a", locale)
    val date = inputDate.parse(this)
    return outputDate.format(date)
}

fun String.formatTanggalDay(): String? {
    val locale = Locale("id")
    val inputDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", locale)
    val outputDate = SimpleDateFormat("EEEE", locale)
    val date = inputDate.parse(this)
    return outputDate.format(date)
}