package com.example.esperanto_menu.model.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channels_Datas


class ToString_Adapter_Channels(
    private val context: Context,
    private val values: List<String>

) : RecyclerView.Adapter<ToString_Adapter_Channels.ViewHolder>() {

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

        private var channelString: String? = null




        init {



            itemView.setOnClickListener {
                //onItemClick?.invoke(ChannelList[adapterPosition])

                val position : Int = adapterPosition
                val action = R.id.action_navigation_channels_to_navigation_episodes



                Log.d("Adapter", "onClick")
                Toast.makeText(itemView.context, "You clicked on + ${channelString} +", Toast.LENGTH_SHORT).show()



            }
        }


        private val textView = view.findViewById<TextView>(R.id.channelName)

        fun bind(item: String) {
            channelString = item
            textView.text = item.capitalize()



            Log.d("TOSTRINGADAPTER", "BIND")
        }
    }

    override fun getItemCount(): Int = values.size
}