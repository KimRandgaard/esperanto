package com.example.esperanto_menu.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.ui.episodes.EpisodesFragmentsDirections
import com.example.esperanto_menu.R.layout.card_view


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
            val action = EpisodesFragmentsDirections.actionNavigationEpisodesToNavigationSpeceficEpisode(
                    episodeName = item.plennomo,
                    episodeDate = item.dato
                )

            //holder..findNavController().navigate(action)
        }
    }

    class HomeViewHolder(val ViewHolder: View) : RecyclerView.ViewHolder(ViewHolder) {

        private val channelName = ViewHolder.findViewById<TextView>(R.id.channelName_cardView)
        private val episodeName = ViewHolder.findViewById<TextView>(R.id.episodeName_cardView)
        private val channelPicture = ViewHolder.findViewById<ImageView>(R.id.episodePic_onCard)

        fun bind(item: Channel) {
            channelName.text = item.nomo
            episodeName.text = item.plennomo + " " + item.dato
            channelPicture

        }
    }

    override fun getItemCount(): Int = values.size

}
