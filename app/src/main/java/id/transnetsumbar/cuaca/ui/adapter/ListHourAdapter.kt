package id.transnetsumbar.cuaca.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.transnetsumbar.cuaca.R
import id.transnetsumbar.cuaca.network.model.ListItem
import id.transnetsumbar.cuaca.untils.formatTanggalToJam
import kotlinx.android.synthetic.main.layout_list_hour.view.*

class ListHourAdapter(private val item: List<ListItem>)
    : RecyclerView.Adapter<ListHourAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_hour, parent, false))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("CheckResult")
        fun bindItem(listItem: ListItem) {
            itemView.tvJam.text = listItem.dtTxt?.formatTanggalToJam()
            itemView.tvCuaca.text = String.format("%s\u00B0", listItem.main?.temp.toString())

            when(listItem.weather?.get(0)?.main.toString()) {
                "Clear" -> {
                    itemView.ivImage.setImageResource(R.drawable.ic_icon___sun)
                }

                "Rain" -> {
                    itemView.ivImage.setImageResource(R.drawable.ic_icon___cloud_rain)
                }

                "Clouds" -> {
                    itemView.ivImage.setImageResource(R.drawable.ic_icon___cloud_lightning)
                }
            }
        }
    }
}