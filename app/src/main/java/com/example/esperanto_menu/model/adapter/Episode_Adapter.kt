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

class Episode_Adapter(
    private val context: Context,
    private val values1: List<Channel>
    )
    : RecyclerView.Adapter<ViewHolder>() {

//      private var titel = titlelList,
//      private var length = lengthList,
//      private var description = descriptionList,

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val episodeInflater = LayoutInflater.from(context)
        return ViewHolder(
            episodeInflater.inflate(R.layout.specefic_episode_reciclerview, parent, false)
        )

        //val v = LayoutInflater.from(parent.context)
        //    .inflate(R.layout.specefic_episode_reciclerview, parent, false)

        //return ViewHolder(v)
    }

    override fun onBindViewHolder(newholder: ViewHolder, newposition: Int) {
        //holder.episodeName.text = titel[position]
        //holder.episodeLength.text = length[position]
        //holder.episodeDescription = description[position]

        val newItem = values1[newposition]
        newholder.Bind(newItem)

    }

    override fun getItemCount(): Int = values1.size

    class ViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val teksto = itemView.findViewById<TextView>(R.id.episodeDescription)
        private val episodeName = itemView.findViewById<TextView>(R.id.episodeName)
       // private val episodeDate = itemView.findViewById<TextView>(R.id.)
       // private val channelName = itemView.findViewById<TextView>(R.id.channelName_OnEpisode)

        fun Bind(item: Channel){
            teksto.text = item.teksto
            episodeName.text = item.plennomo

           // channelName.text = item.nomo.toString()
        }


//        var episodeLength: TextView
//        var episodeName: TextView
//        var episodeDescription: TextView

//        init {
//            episodeLength = itemView.findViewById(R.id.episodeLenght)
//            episodeName = itemView.findViewById(R.id.episodeName)
//            episodeDescription = itemView.findViewById(R.id.episodeDescription)
//
//
//            fun bind(item: Channel) {
//                episodeName.text = item.plennomo
//                //  episodeLength.text = item.(find en måde at få tiden /længen på mp3filen)
//                episodeDescription.text = item.teksto
//
//            }
//            itemView.setOnClickListener {
//                Toast.makeText(itemView.context, "you clicked on ${episodeName}", Toast.LENGTH_LONG)
//                    .show()
//                //skal sendes videre til den givne episode sidde.
//            }
//        }

    }
}