package com.example.esperanto_menu.model.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channels_Datas
import androidx.navigation.fragment.findNavController
import com.example.esperanto_menu.ui.channel.ChannelFragmentDirections
import com.example.esperanto_menu.ui.home.HomeFragment


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


        holder.itemView.setOnClickListener {
            //onItemClick?.invoke(ChannelList[adapterPosition])

          //  val position : Int = adapterPosition





         val action = ChannelFragmentDirections.actionNavigationChannelsToNavigationEpisodes(channelName = item)
             //R.id.action_navigation_channels_to_navigation_episodes

            holder.view.findNavController().navigate(action)


            Log.d("Adapter", "onClick")
            Toast.makeText(holder.itemView.context, "You clicked on + ${item} +", Toast.LENGTH_SHORT).show()



        }
    }

    class ViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private var channelString: String? = null




        init {




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