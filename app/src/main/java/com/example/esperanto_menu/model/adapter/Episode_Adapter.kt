package com.example.esperanto_menu.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.adapter.Episode_Adapter.ViewHolder
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.util.getDuration

class Episode_Adapter(
    private val context: Context,
    private val values1: List<Channel>
    )
    : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val episodeInflater = LayoutInflater.from(context)
        return ViewHolder(
            episodeInflater.inflate(R.layout.specefic_episode_reciclerview, parent, false)
        )

    }

    override fun onBindViewHolder(newholder: ViewHolder, newposition: Int) {

        val newItem = values1[newposition]
        newholder.Bind(newItem)

    }

    override fun getItemCount(): Int = values1.size

    class ViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val teksto = itemView.findViewById<TextView>(R.id.episodeDescription)
        private val episodeName = itemView.findViewById<TextView>(R.id.episodeName)
        private val episodeLength = itemView.findViewById<TextView>(R.id.episodeLenght)

        fun Bind(item: Channel){
            teksto.text = item.teksto
            episodeName.text = item.plennomo +" "+ item.dato
            episodeLength.text = getDuration(item.mp3fajlo)

        }

    }
}