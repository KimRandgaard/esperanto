package com.example.esperanto_menu.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.util.getDuration

class Specefik_Episode_Adapter (
    private val context: Context,
    private val values2: List<Channel>
)
    : RecyclerView.Adapter<Specefik_Episode_Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val episodeInflater = LayoutInflater.from(context)
        return ViewHolder(
            episodeInflater.inflate(R.layout.specefic_episode, parent, false)
        )

    }

    override fun onBindViewHolder(newholder: ViewHolder, newposition: Int) {

        val newItem = values2[newposition]
        newholder.Bind(newItem)

    }

    override fun getItemCount(): Int = values2.size


    class ViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private val nomo = view.findViewById<TextView>(R.id.channelName_OnEpisode)
        private val teksto = view.findViewById<TextView>(R.id.episodeDescription_episode)
        private val episodeName = view.findViewById<TextView>(R.id.episodeName_episode)
        private val episodeLength = view.findViewById<TextView>(R.id.episodeLenght_episode)

        fun Bind(item: Channel) {
            nomo.text = item.nomo
            teksto.text = item.teksto
            episodeName.text = item.plennomo + " " + item.dato
            episodeLength.text = getDuration(item.mp3fajlo)
//todo(get this showen!!!!!)
        }
    }
}