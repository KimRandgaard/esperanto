package com.example.esperanto_menu.model.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channel


class Channel_Adapter(
    private val ChannelList: ArrayList<Channel>,
    private val context: Context,
    private val values: List<Channel>,
    var onItemClick: ((Channel) -> Unit)? = null


) : RecyclerView.Adapter<Channel_Adapter.ChannelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val inflater = LayoutInflater.from(context)
        return ChannelViewHolder(
            inflater.inflate(R.layout.channel_list, parent, false)
        )

    }


    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val item : Channel = values[position]

        holder.bind(item)
    }

    class ChannelViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                //onItemClick?.invoke(ChannelList[adapterPosition])
                Log.d("Adapter", "onClick")

            }
        }

        private val textView = itemView.findViewById<TextView>(R.id.channelName)

        fun bind(item: Channel) {
            textView.text= item.nomo.toString()
        }
    }

    override fun getItemCount(): Int = ChannelList.size

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val clickableChannel: TextView = itemView.findViewById(R.id.channelName)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(ChannelList[adapterPosition])
                Toast.makeText(context, "Onclick", Toast.LENGTH_SHORT).show()
            }
        }
    }


}


