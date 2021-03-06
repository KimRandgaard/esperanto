package com.example.esperanto_menu.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.R.layout.card_view
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.ui.home.HomeFragmentDirections


class Home_Adapter(
    private val values: List<Channel>,

) : RecyclerView.Adapter<Home_Adapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
              // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(card_view, parent, false)

        return HomeViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationSpeceficEpisode(episodeName = item.plennomo, episodeDate = item.dato)

            holder.itemView.findNavController().navigate(action)
        }
    }

    class HomeViewHolder(val ViewHolder: View) : RecyclerView.ViewHolder(ViewHolder) {

        private val channelName = ViewHolder.findViewById<TextView>(R.id.channelName_cardView)
        private val episodeName = ViewHolder.findViewById<TextView>(R.id.episodeName_cardView)
       // private val channelPicture = ViewHolder.findViewById<ImageView>(R.id.episodePic_onCard)
        private val episodeDate = ViewHolder.findViewById<TextView>(R.id.episodeDate_cardVies)

        fun bind(item: Channel) {
            channelName.text = item.nomo
            episodeName.text = item.plennomo
            episodeDate.text = item.dato
           // channelPicture

        }
    }

    override fun getItemCount(): Int = values.size

}
