package com.example.esperanto_menu.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.ui.episodes.EpisodesFragmentsDirections

class Episode_Adapter(
private val context: Context,
private val values: List<Channel>
) : RecyclerView.Adapter<Episode_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val episodeInflater = LayoutInflater.from(context)
        return ViewHolder(
            episodeInflater.inflate(R.layout.specefic_episode_reciclerview, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            val action = EpisodesFragmentsDirections.actionNavigationEpisodesToNavigationSpeceficEpisode(episodeName = item.plennomo, episodeDate = item.dato)

            holder.view.findNavController().navigate(action)
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        private val teksto = view.findViewById<TextView>(R.id.episodeDescription)
        private val episodeName = view.findViewById<TextView>(R.id.episodeName)
        private val episodeLength = view.findViewById<TextView>(R.id.episodeLenght)

        fun bind(item: Channel){
            teksto.text = item.teksto
            episodeName.text = item.plennomo +" "+ item.dato
            // episodeLength.text = getDuration(item.mp3fajlo)

        }
    }
    override fun getItemCount(): Int = values.size
}

