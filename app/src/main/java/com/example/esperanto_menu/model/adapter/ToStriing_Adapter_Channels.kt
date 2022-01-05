package com.example.esperanto_menu.model.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.model.data.Channels_Datas
import java.util.concurrent.TimeoutException


class ToStriing_Adapter_Channels(
    private val context: Context,
    private val values: List<String>

) : RecyclerView.Adapter<ToStriing_Adapter_Channels.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            inflater.inflate(R.layout.channel_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    class ViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        private val textView = view.findViewById<TextView>(R.id.channelName)

        fun bind(item: String) {
            textView.text= item
        }
    }

    override fun getItemCount(): Int = values.size
}