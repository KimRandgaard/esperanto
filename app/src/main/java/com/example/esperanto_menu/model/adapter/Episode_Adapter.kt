package com.example.esperanto_menu.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.model.data.Channel

class Episode_Adapter: RecyclerView.Adapter<Episode_Adapter.ViewHolder>() {

    //  private var titel = titlelList,
    //  private var length = lengthList,
    //  private var description = descriptionList,

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Episode_Adapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.specefic_episode_reciclerview, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: Episode_Adapter.ViewHolder, position: Int) {
        holder.episodeName.text = titel[position]
        holder.episodeLength.text = length[position]
        holder.episodeDescription = description[position]
    }

    override fun getItemCount(): Int {
        return titel.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var episodeLength: TextView
        var episodeName: TextView
        var episodeDescription: TextView

        init {
            episodeLength = itemView.findViewById(R.id.episodeLenght)
            episodeName = itemView.findViewById(R.id.episodeName)
            episodeDescription = itemView.findViewById(R.id.episodeDescription)


            fun bind(item: Channel) {
                episodeName.text = item.plennomo
                //  episodeLength.text = item.(find en måde at få tiden /længen på mp3filen)
                episodeDescription.text = item.teksto

            }
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "you clicked on ${episodeName}", Toast.LENGTH_LONG)
                    .show()
                //skal sendes videre til den givne episode sidde.
            }
        }

    }
}


