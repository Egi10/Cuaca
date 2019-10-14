package id.transnetsumbar.cuaca.ui.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.transnetsumbar.cuaca.R
import id.transnetsumbar.cuaca.network.model.City
import id.transnetsumbar.cuaca.network.model.ListItem
import id.transnetsumbar.cuaca.ui.adapter.ListDayAdapter
import id.transnetsumbar.cuaca.ui.adapter.ListHourAdapter
import id.transnetsumbar.cuaca.untils.formatTanggal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var mainPresenter: MainPresenter
    private var listHour: MutableList<ListItem> = mutableListOf()
    private var listDay: MutableList<ListItem> = mutableListOf()
    private lateinit var listHourAdapter: ListHourAdapter
    private lateinit var listDayAdapter: ListDayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        mainPresenter.getCuaca()

        listHourAdapter = ListHourAdapter(listHour)
        rvCuacaHour.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCuacaHour.adapter = listHourAdapter

        listDayAdapter = ListDayAdapter(listDay)
        rvCuacaDay.layoutManager = LinearLayoutManager(this)
        rvCuacaDay.adapter = listDayAdapter
    }

    override fun onSuccess(list: List<ListItem>?, city: City?) {
        city?.let {
            tvTempat.text = it.name
        }

        list?.let {
            tvTanggal.text = it[0].dtTxt?.formatTanggal()
            tvSuhu.text = String.format("%s\u00B0", it[0].main?.temp.toString())
            tvCuaca.text = it[0].weather?.get(0)?.main.toString()

            when(it[0].weather?.get(0)?.main.toString()) {
                "Clear" -> {
                    ivImageSuhu.setImageResource(R.drawable.ic_icon___sun)
                }

                "Rain" -> {
                    ivImageSuhu.setImageResource(R.drawable.ic_icon___cloud_rain)
                }

                "Clouds" -> {
                    ivImageSuhu.setImageResource(R.drawable.ic_icon___cloud_lightning)
                }
            }
        }

        listDay.clear()
        list?.let {
            listDay.addAll(it)
        }
        listDayAdapter.notifyDataSetChanged()
    }

    override fun onSuccessHour(list: List<ListItem>?) {
        listHour.clear()
        list?.let {
            listHour.addAll(it)
        }
        listHourAdapter.notifyDataSetChanged()
    }

    override fun onError(error: String?) {
        error?.let {
            Log.d("Error", it)
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }
}
