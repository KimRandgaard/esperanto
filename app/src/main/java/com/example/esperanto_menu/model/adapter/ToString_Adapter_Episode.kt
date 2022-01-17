package com.example.esperanto_menu.model.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.ui.episodes.EpisodesFragmentsDirections
import okio.Utf8.size
import java.nio.file.Files.size


class ToString_Adapter_Episode(
    private val context: Context,
    private val values: List<String>
) : RecyclerView.Adapter<ToString_Adapter_Episode.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToString_Adapter_Episode.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            inflater.inflate(R.layout.specefic_episode_reciclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {

            val action = EpisodesFragmentsDirections.actionNavigationEpisodesToNavigationSpeceficEpisode(episodeName = item)

            holder.view.findNavController().navigate(action)

            Log.d("Adapter","onClick")
            Toast.makeText(holder.itemView.context,"You clicked on + ${item} +",Toast.LENGTH_SHORT).show()
        }

    }


    class ViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private var episodeString: String? = null


        init {


        }


        private val textView = view.findViewById<TextView>(R.id.episodeName)

        fun bind(item: String) {
            episodeString = item
            textView.text = item.capitalize()

            Log.d("TostringAdapter", "Bind")

        }

    }

    override fun getItemCount(): Int = values.size
}