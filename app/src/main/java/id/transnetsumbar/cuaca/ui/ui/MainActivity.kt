package id.transnetsumbar.cuaca.ui.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.transnetsumbar.cuaca.R
import id.transnetsumbar.cuaca.network.model.ListItem
import id.transnetsumbar.cuaca.ui.adapter.ListDayAdapter
import id.transnetsumbar.cuaca.ui.adapter.ListHourAdapter
import id.transnetsumbar.cuaca.untils.formatTanggal
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var listHour: MutableList<ListItem> = mutableListOf()
    private var listDay: MutableList<ListItem> = mutableListOf()
    private lateinit var listHourAdapter: ListHourAdapter
    private lateinit var listDayAdapter: ListDayAdapter

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        with(viewModel) {
            state.observe(this@MainActivity, Observer {
                swipeRefresh.isRefreshing = it
            })

            itemHour.observe(this@MainActivity, Observer {
                listHour.clear()
                it?.let {
                    listHour.addAll(it)
                }
                listHourAdapter.notifyDataSetChanged()
            })

            item.observe(this@MainActivity, Observer {
                it?.let {
                    tvTanggal.text = it[0].dtTxt?.formatTanggal()
                    tvSuhu.text = String.format("%s\u00B0", it[0].main?.temp.toString())
                    tvCuaca.text = it[0].weather?.get(0)?.main.toString()

                    when (it[0].weather?.get(0)?.main.toString()) {
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
                it?.let {
                    listDay.addAll(it)
                }
                listDayAdapter.notifyDataSetChanged()
            })

            thePlace.observe(this@MainActivity, Observer {
                tvTempat.text = it
            })

            error.observe(this@MainActivity, Observer {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            })
        }

        listHourAdapter = ListHourAdapter(listHour)
        rvCuacaHour.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCuacaHour.adapter = listHourAdapter

        listDayAdapter = ListDayAdapter(listDay)
        rvCuacaDay.layoutManager = LinearLayoutManager(this)
        rvCuacaDay.adapter = listDayAdapter
    }
}
